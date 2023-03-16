package com.nihongo.common.exception;

public class LetterExistedException extends Exception{
    public LetterExistedException() {
    }

    public LetterExistedException(String message) {
        super(message);
    }

    public LetterExistedException(String message, Throwable cause) {
        super(message, cause);
    }
}
