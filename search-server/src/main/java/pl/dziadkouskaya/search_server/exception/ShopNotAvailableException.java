package pl.dziadkouskaya.search_server.exception;

public class ShopNotAvailableException extends RuntimeException {
    public ShopNotAvailableException() {
    }

    public ShopNotAvailableException(String message) {
        super(message);
    }

    public ShopNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopNotAvailableException(Throwable cause) {
        super(cause);
    }

    public ShopNotAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
