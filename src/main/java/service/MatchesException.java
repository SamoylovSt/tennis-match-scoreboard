package service;

public class MatchesException extends RuntimeException {
    public MatchesException(String message) {
        super(message);
    }

    public MatchesException(Throwable ex){
        super(ex);
    }
}