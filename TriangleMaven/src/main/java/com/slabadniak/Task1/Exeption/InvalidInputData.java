package com.slabadniak.Task1.Exeption;

public class InvalidInputData extends Exception {
    public InvalidInputData(){}

    public InvalidInputData(String message){
        super(message);
    }

    public InvalidInputData(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputData(Throwable cause) {
        super(cause);
    }
}