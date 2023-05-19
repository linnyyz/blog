package com.linny.blog.exceptions;

public class AllException extends Exception{
    public AllException() {
    }

    public AllException(String message) {
        super(message);
    }

    public AllException(String message, Throwable cause) {
        super(message, cause);
    }

    public AllException(Throwable cause) {
        super(cause);
    }

    public AllException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
