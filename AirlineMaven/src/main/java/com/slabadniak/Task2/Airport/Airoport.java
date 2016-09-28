package com.slabadniak.Task2.Airport;

import com.slabadniak.Task2.Airport.ClassOfAirport.classOfAirport;

public class Airoport {
    private static long INCR;
    private long Id = ++INCR;
    private String name;
    private String location;
    private int numOfTerminals;
    private classOfAirport classofairport;

    public Airoport(String name, String location,int numOfTerminals,classOfAirport classofairport) {
        this.name = name;
        this.numOfTerminals = numOfTerminals;
        this.location = location;
        this.classofairport = classofairport;
    }

    public long getId() {
        return Id;
    }
}
