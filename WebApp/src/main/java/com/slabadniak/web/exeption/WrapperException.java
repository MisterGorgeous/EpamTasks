package com.slabadniak.web.exeption;


/**
 * Represent the wrapper's exeption.
 * @author Slabadniak Sergei
 * @version 1.0
 */
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
