package com.rest.usermanagment.exceptions;


public class UserGroupNotFoundException extends RuntimeException   {

    public UserGroupNotFoundException() {
    }

    public UserGroupNotFoundException(String message) {
        super(message);
    }

    public UserGroupNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserGroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserGroupNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
