package com.nihongo.common.exception;

public class LetterNotFoundException extends Exception{
    public LetterNotFoundException() {
    }

    public LetterNotFoundException(String message) {
        super(message);
    }

    public LetterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
