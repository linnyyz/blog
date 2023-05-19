package com.linny.blog.exceptions;

public class UserIsBeenBandException extends AllException{
    public UserIsBeenBandException() {
    }

    public UserIsBeenBandException(String message) {
        super(message);
    }

    public UserIsBeenBandException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsBeenBandException(Throwable cause) {
        super(cause);
    }

    public UserIsBeenBandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
