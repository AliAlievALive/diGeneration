package tech.itpark.di;

public class ObjectInstantiationException extends DIException {
    public ObjectInstantiationException() {
        super();
    }

    public ObjectInstantiationException(String message) {
        super(message);
    }

    public ObjectInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectInstantiationException(Throwable cause) {
        super(cause);
    }

    protected ObjectInstantiationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
