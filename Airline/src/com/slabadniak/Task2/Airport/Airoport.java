package com.slabadniak.Task2.Airport;

import com.slabadniak.Task2.Airport.ClassOfAirport.classOfAirport;
import com.sun.corba.se.impl.orbutil.graph.Graph;

public class Airoport {
    private String name;
    private String location;
    private int numOfLandingStrips;
    private classOfAirport classofairport;

    public Airoport(String name, int numOfLandingStrips, String location,classOfAirport classofairport) {
        this.name = name;
        this.numOfLandingStrips = numOfLandingStrips;
        this.location = location;
        this.classofairport = classofairport;
    }
}
