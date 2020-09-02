package me.mrdaniel.ageofittgard.exception;

public class InvalidQuestException extends Exception {

    public InvalidQuestException() {
    }

    public InvalidQuestException(String message) {
        super(message);
    }

    public InvalidQuestException(Throwable cause) {
        super(cause);
    }

    public InvalidQuestException(String message, Throwable cause) {
        super(message, cause);
    }
}
