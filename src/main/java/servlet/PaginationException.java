package servlet;

public class PaginationException extends RuntimeException {
    public PaginationException(String message) {
        super(message);
    }

    public PaginationException(Throwable ex) {
        super((ex));
    }
}
