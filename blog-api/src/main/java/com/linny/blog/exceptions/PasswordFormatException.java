package com.linny.blog.exceptions;

public class PasswordFormatException extends AllException{
    public PasswordFormatException() {
    }

    public PasswordFormatException(String message) {
        super(message);
    }

    public PasswordFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordFormatException(Throwable cause) {
        super(cause);
    }

    public PasswordFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
