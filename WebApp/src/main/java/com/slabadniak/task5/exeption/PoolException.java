package com.slabadniak.task5.exeption;

public class PoolException extends Exception {
    public PoolException() {
        super();
    }

    public PoolException(String message) {
        super(message);
    }

    public PoolException(String message, Throwable cause) {
        super(message, cause);
    }

    protected PoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
