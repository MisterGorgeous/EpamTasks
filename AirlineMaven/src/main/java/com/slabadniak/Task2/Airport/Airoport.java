package com.slabadniak.Task2.Airport;

import com.slabadniak.Task2.Airport.AirportsClass.AirportsClass;
import com.slabadniak.Task2.Airport.LocationOfAirportsCity.AirportLocationCity;

public class Airoport {
    private static long INCR;
    private long Id = ++INCR;
    private String name;
    private AirportLocationCity locationCity;
    private String locationCountry;
    private int numOfTerminals;
    private AirportsClass classofairport;

    public Airoport(String name, AirportLocationCity locationCity, String locationCountry, int numOfTerminals, AirportsClass classofairport) {
        this.name = name;
        this.numOfTerminals = numOfTerminals;
        this.locationCity = locationCity;
        this.locationCountry = locationCountry;
        this.classofairport = classofairport;
    }

    public long getId() {
        return Id;
    }

    public AirportLocationCity getLocationCity() {
        return locationCity;
    }

    @Override
    public String toString() {
        return "Airport " + name + " situated in " + locationCity.toString() + "-" + locationCountry + "witch class is "
                + classofairport.toString();
    }
}
