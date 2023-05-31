package hu.czikk;

public class IncorrectFileNameException extends Exception {
    public IncorrectFileNameException(String errorMessage, Throwable err) {
        super(errorMessage);
    }
}
