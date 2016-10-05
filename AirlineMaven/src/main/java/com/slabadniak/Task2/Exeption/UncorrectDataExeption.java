package com.slabadniak.task2.exeption;


public class UncorrectDataExeption extends Exception {
    public UncorrectDataExeption() {
    }

    public UncorrectDataExeption(String message) {
        super(message);
    }

    public UncorrectDataExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public UncorrectDataExeption(Throwable cause) {
        super(cause);
    }
}
