package com.slabadniak.Task1.Exeption;

public class NotATriangleExeption extends Exception {
    public NotATriangleExeption(){}

    public NotATriangleExeption(String message){
        super(message);
    }

    public NotATriangleExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public NotATriangleExeption(Throwable cause) {
        super(cause);
    }
}

