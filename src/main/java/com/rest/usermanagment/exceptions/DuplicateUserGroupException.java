package com.rest.usermanagment.exceptions;

public class DuplicateUserGroupException extends Exception {
    public DuplicateUserGroupException() {
    }

    public DuplicateUserGroupException(String message) {
        super(message);
    }

    public DuplicateUserGroupException(Throwable cause) {
        super(cause);
    }

    public DuplicateUserGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUserGroupException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
