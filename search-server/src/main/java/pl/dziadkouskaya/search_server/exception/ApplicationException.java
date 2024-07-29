package pl.dziadkouskaya.search_server.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }
}
