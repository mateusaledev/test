package br.com.pulsewarp.application.exception;

public class DatabaseExecutionException extends RuntimeException {

    public DatabaseExecutionException() {
        super();
    }

    public DatabaseExecutionException(String message) {
        super(message);
    }

    public DatabaseExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseExecutionException(Throwable cause) {
        super(cause);
    }
}
