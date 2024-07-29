package pl.dziadkouskaya.graphql.exception;

public class ResourceExistedException extends RuntimeException {
    public ResourceExistedException() {
    }

    public ResourceExistedException(String message) {
        super(message);
    }
}
