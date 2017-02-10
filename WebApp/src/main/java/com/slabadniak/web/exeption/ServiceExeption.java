package com.slabadniak.web.exeption;


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
