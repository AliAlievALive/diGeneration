package tech.itpark.di;

public class AmbiguousConstructorException extends DIException {
    public AmbiguousConstructorException() {
        super();
    }

    public AmbiguousConstructorException(String message) {
        super(message);
    }

    public AmbiguousConstructorException(String message, Throwable cause) {
        super(message, cause);
    }

    public AmbiguousConstructorException(Throwable cause) {
        super(cause);
    }

    protected AmbiguousConstructorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
