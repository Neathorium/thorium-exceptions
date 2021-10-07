package com.neathorium.thorium.exceptions.exception;

public class ArgumentNullException extends IllegalArgumentException {
    public ArgumentNullException(String message) {
        super(message);
    }

    public ArgumentNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
