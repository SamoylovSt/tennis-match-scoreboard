package dao;

public class DaoException extends RuntimeException {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable ex) {
        super(ex);
    }
}
