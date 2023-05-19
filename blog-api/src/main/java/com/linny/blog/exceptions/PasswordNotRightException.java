package com.linny.blog.exceptions;

public class PasswordNotRightException extends AllException{
    public PasswordNotRightException() {
    }

    public PasswordNotRightException(String message) {
        super(message);
    }

    public PasswordNotRightException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotRightException(Throwable cause) {
        super(cause);
    }

    public PasswordNotRightException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
