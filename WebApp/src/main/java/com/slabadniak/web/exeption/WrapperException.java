package com.slabadniak.web.exeption;


public class WrapperException extends Exception {
    public WrapperException() {
    }

    public WrapperException(String message) {
        super(message);
    }

    public WrapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrapperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
