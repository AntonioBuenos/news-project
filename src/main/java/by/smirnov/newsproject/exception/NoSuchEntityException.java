package by.smirnov.newsproject.exception;

import static by.smirnov.newsproject.constants.ResponseEntityConstants.NOT_FOUND_MESSAGE;

public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException() {
        super(NOT_FOUND_MESSAGE);
    }

    @Override
    public String toString() {
        return "NoSuchEntityException{}" + super.toString();
    }
}
