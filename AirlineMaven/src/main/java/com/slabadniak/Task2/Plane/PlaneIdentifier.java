package com.slabadniak.task2.plane;

public class PlaneIdentifier {
    private PlaneIdentifier(){}
    private static long id;
    public static long getNextId() {
        return ++id;
    }
}
