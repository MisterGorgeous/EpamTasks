package com.slabadniak.Task1.Exeption;

public class InvalidInputDataExeption extends Exception {
    public InvalidInputDataExeption(){}

    public InvalidInputDataExeption(String message){
        super(message);
    }

    public InvalidInputDataExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputDataExeption(Throwable cause) {
        super(cause);
    }
}