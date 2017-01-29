package com.slabadniak.web.exeption;


public class CommandExeption extends Exception{
    public CommandExeption() {
    }

    public CommandExeption(String message) {
        super(message);
    }

    public CommandExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
