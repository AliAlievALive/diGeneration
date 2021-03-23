package tech.itpark.di;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        try {
            final var container = new Container();
            // ObjectRegistrator
//            container.register((Function<String, SmsClient>)(@Inject("smsUrl") String smsUrl) -> {
//                return new SmsClient(smsUrl);
//            });
            container.register("smsUrl", "https://sms.io");
            container.register("pushUrl", "https://firebase.io");
            container.register(SmsClient.class, PushClient.class, Service.class, RepositoryStubImpl.class);
            container.wire();
            // Server'ов:
            // 1. Startup
            // 2. Handle request
            System.out.println("finish");
        } catch (DIException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}

// 1. Откуда брать готовые значения: String, List<String>
// 2. Как различать параметры конструктора одинаковых типов
class SmsClient {
    private final String url;

    public SmsClient(@Inject("smsUrl") String url) {
        this.url = url;
    }
}

class PushClient {
    private final String url;

    public PushClient(@Inject("pushUrl") String url) {
        this.url = url;
    }
}

interface InjectableToMyService {

}

class RepositoryStubImpl implements Repository {
    @Override
    public List<Object> getAll() {
        return Collections.emptyList();
    }
}

class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }
}
