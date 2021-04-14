package tech.itpark.di;

public class DIException extends RuntimeException{
    public DIException() {
        super();
    }

    public DIException(String message) {
        super(message);
    }

    public DIException(String message, Throwable cause) {
        super(message, cause);
    }

    public DIException(Throwable cause) {
        super(cause);
    }

    protected DIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
