package tech.itpark.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class Container {
    private final Map<String, Object> values = new HashMap<>();
    private final Map<Class<?>, Object> objects = new HashMap<>();
    private final Set<Class<?>> definitions = new HashSet<>();

    public void register(Class<?>... definitions) {
        String badDefinitions = Arrays.stream(definitions)
                .filter(o -> o.getDeclaredConstructors().length != 1)
                .map(o -> o.getName())
                .collect(Collectors.joining(", "));
        if (!badDefinitions.isEmpty()) {
            throw new AmbiguousConstructorException(badDefinitions);
        }

        this.definitions.addAll(Arrays.asList(definitions));
    }

    public void register(String name, Object value) {
        if (values.containsKey(name)) {
            throw new AmbiguousValueNameException(String.format("%s with value %s", name, value.toString()));
        }

        values.put(name, value);
    }

    public void wire() {
        HashSet<Class<?>> todo = new HashSet<>(definitions);

        while (todo.size() > 0) {
            // -> .. -> .. -> .. ->
            final var generation = todo.stream() // lazy
                    .map(o -> o.getDeclaredConstructors()[0])
                    .filter(o -> o.getParameterCount() == 0 || allParameterInValues(o))
                    .map(o -> {
                        try {
                            o.setAccessible(true);
                            List<Object> params = Arrays.stream(o.getParameters())
                                    .map(p -> Optional.ofNullable(objects.get(p.getType()))
                                            .or(() -> Optional.ofNullable(values.get(
                                                    // TODO: NPE
                                                    p.getAnnotation(Inject.class).value() // arg0
                                            )))
                                            .orElseThrow(() -> new UnmetDependenciesException(p.getName()))
                                    )
                                    .collect(Collectors.toList());
                            return o.newInstance(params.toArray());
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                            throw new ObjectInstantiationException(e); // <- e //
                        }
                    })
                    .collect(Collectors.toMap(o -> o.getClass(), o -> o));
            objects.putAll(generation);

            // TODO: clean code
            generation.entrySet().stream()
                    .map(o -> {
                        final var interfaces = o.getKey().getInterfaces();
                        final var value = o.getValue();
                        final var ifaces = new HashMap<Class<?>, Object>();
                        for (Class<?> cls : interfaces) {
                            ifaces.put(cls, value);
                        }
                        return ifaces;
                    }).forEach(o -> {
                objects.putAll(o);
            });

            todo.removeAll(generation.keySet());

            if (generation.size() == 0) {
                // sad path
                String unmet = todo.stream()
                        .map(o -> o.getName())
                        .collect(Collectors.joining(", "));
                throw new UnmetDependenciesException(unmet);
            }
        }
    }

    private boolean allParameterInValues(Constructor<?> constructor) {
        final var parameters = new HashSet<>(Arrays.asList(constructor.getParameters()));
        parameters.removeIf(p -> objects.containsKey(p.getType()));
        // TODO: check parameter annotation -> throw exception
        // Service
        ;
        parameters.removeAll(
                parameters.stream()
                        .filter(p -> p.isAnnotationPresent(Inject.class))
                        .filter(p -> values.containsKey(p.getAnnotation(Inject.class).value()))
                        .collect(Collectors.toList())
                // "smsUrl", "pushUrl"
        );
        return parameters.isEmpty();
    }
}
