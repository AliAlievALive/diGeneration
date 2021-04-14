package tech.itpark.di;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Retention(value = RetentionPolicy.RUNTIME)
// value - спец.имя: если других элементов или их значения выставлены по умолчанию, то value никто не пишет
// массивы - {}, но если 1 элемент - то без
// если есть default (все default) или нет элементов, то круглые скобки тоже можно не писать
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Inject {
    String value();
}
