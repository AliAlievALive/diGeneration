package tech.itpark.di;

public class UnmetDependenciesException extends DIException {
    public UnmetDependenciesException() {
        super();
    }

    public UnmetDependenciesException(String message) {
        super(message);
    }

    public UnmetDependenciesException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnmetDependenciesException(Throwable cause) {
        super(cause);
    }

    protected UnmetDependenciesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
