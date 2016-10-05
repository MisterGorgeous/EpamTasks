package com.slabadniak.task2.airport;

import com.slabadniak.task2.airport.airportsclass.AirportsClass;
import com.slabadniak.task2.airport.airportlocation.AirportLocationCity;
import com.slabadniak.task2.identifier.Identifier;

public class Airoport {
    private static Identifier identifier = new Identifier();
    private long id;
    private String name;
    private AirportLocationCity locationCity;
    private String locationCountry;
    private int numOfTerminals;
    private AirportsClass classofairport;

    public Airoport(String name, AirportLocationCity locationCity, String locationCountry, int numOfTerminals, AirportsClass classofairport) {
        this.id = identifier.getNextId();
        this.name = name;
        this.numOfTerminals = numOfTerminals;
        this.locationCity = locationCity;
        this.locationCountry = locationCountry;
        this.classofairport = classofairport;
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
