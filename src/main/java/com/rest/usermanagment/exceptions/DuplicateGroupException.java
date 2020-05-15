package com.rest.usermanagment.exceptions;

public class DuplicateGroupException extends Exception {
    public DuplicateGroupException() {
    }

    public DuplicateGroupException(String message) {
        super(message);
    }

    public DuplicateGroupException(Throwable cause) {
        super(cause);
    }

    public DuplicateGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateGroupException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
