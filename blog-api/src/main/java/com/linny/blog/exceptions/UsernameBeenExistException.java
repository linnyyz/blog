package com.linny.blog.exceptions;

public class UsernameBeenExistException extends AllException{
    public UsernameBeenExistException() {
    }

    public UsernameBeenExistException(String message) {
        super(message);
    }

    public UsernameBeenExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameBeenExistException(Throwable cause) {
        super(cause);
    }

    public UsernameBeenExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
