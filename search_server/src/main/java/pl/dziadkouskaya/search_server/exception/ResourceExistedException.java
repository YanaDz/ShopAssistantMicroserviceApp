package pl.dziadkouskaya.search_server.exception;

public class ResourceExistedException extends RuntimeException {
    public ResourceExistedException() {
    }

    public ResourceExistedException(String message) {
        super(message);
    }

    public ResourceExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceExistedException(Throwable cause) {
        super(cause);
    }

    public ResourceExistedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
