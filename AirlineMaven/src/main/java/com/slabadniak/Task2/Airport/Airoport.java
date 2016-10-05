package com.slabadniak.task2.airport;

import com.slabadniak.task2.plane.PlaneIdentifier;

public class Airoport {
    private long id;
    private String name;
    private AirportLocationCity locationCity;
    private String locationCountry;
    private int numOfTerminals;
    private AirportsClass classofairport;

    public Airoport(String name, AirportLocationCity locationCity, String locationCountry, int numOfTerminals, AirportsClass classofairport) {
        this.id = AirportIndefier.getNextId();
        this.name = name;
        this.numOfTerminals = numOfTerminals;
        this.locationCity = locationCity;
        this.locationCountry = locationCountry;
        this.classofairport = classofairport;
    }

    public Airoport() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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
