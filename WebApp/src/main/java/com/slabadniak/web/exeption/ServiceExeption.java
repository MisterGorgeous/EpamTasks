package com.slabadniak.web.exeption;

/**
 * Represent the exeption on service level.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class ServiceExeption  extends Exception {
    public ServiceExeption() {
    }

    public ServiceExeption(String message) {

        super(message);
    }

    public ServiceExeption(String message, Throwable cause) {
        super(message, cause);
    }
    public ServiceExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
