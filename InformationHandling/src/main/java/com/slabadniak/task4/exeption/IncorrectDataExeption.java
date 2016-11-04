package com.slabadniak.task4.exeption;


public class IncorrectDataExeption extends Exception {
    public IncorrectDataExeption() {
    }

    public IncorrectDataExeption(String message) {
        super(message);
    }

    public IncorrectDataExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataExeption(Throwable cause) {
        super(cause);
    }
}
