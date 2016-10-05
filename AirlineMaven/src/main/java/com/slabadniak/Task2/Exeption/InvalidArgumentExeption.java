package com.slabadniak.task2.exeption;


public class InvalidArgumentExeption extends Exception {
    public InvalidArgumentExeption() {
    }

    public InvalidArgumentExeption(String message) {
        super(message);
    }

    public InvalidArgumentExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentExeption(Throwable cause) {
        super(cause);
    }
}
