package com.slabadniak.task2.airport;

public class AirportIndefier {
    private static long id;
    public static long getNextId() {
        return ++id;
    }
}
