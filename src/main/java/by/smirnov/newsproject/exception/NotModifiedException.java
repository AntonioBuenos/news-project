package by.smirnov.newsproject.exception;

import static by.smirnov.newsproject.constants.ResponseEntityConstants.ALREADY_DELETED_MESSAGE;

public class NotModifiedException extends RuntimeException{

    public NotModifiedException(String message) {
        super(message);
    }

    public NotModifiedException() {
        super(ALREADY_DELETED_MESSAGE);
    }

    @Override
    public String toString() {
        return "NotModifiedException{}" + super.toString();
    }
}
