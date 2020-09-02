package me.mrdaniel.ageofittgard.exception;

public class InvalidPlayerDataException extends Exception {

    public InvalidPlayerDataException() {
    }

    public InvalidPlayerDataException(String message) {
        super(message);
    }

    public InvalidPlayerDataException(Throwable cause) {
        super(cause);
    }

    public InvalidPlayerDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
