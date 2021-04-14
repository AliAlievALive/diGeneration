package tech.itpark.di;

public class AmbiguousValueNameException extends DIException {
    public AmbiguousValueNameException() {
        super();
    }

    public AmbiguousValueNameException(String message) {
        super(message);
    }

    public AmbiguousValueNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public AmbiguousValueNameException(Throwable cause) {
        super(cause);
    }

    protected AmbiguousValueNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
