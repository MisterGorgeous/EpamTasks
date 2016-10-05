package com.slabadniak.task2.report;

import com.slabadniak.task2.airport.AirportLocationCity;

public class Report {
    private AirportLocationCity origin;
    private AirportLocationCity destination;
    private String  planeName;
    private int flyDistance;
    private float routeTime;
    private float flyCost;

    public Report(AirportLocationCity origin, AirportLocationCity destination, String  planeName, int flyDistance, float routeTime, float flycost) {
        this.origin = origin;
        this.destination = destination;
        this.planeName = planeName;
        this.flyDistance = flyDistance;
        this.routeTime = routeTime;
        this.flyCost = flycost;
    }

    public Report() {

    }

    public String showInfo(){
        return "Your have flied from" + origin + "\nto " + destination + ".\n"+
                "On the palne " + planeName + ".The distance was " + flyDistance + ".\n Flying time was " + routeTime
                +" hours.\nThe flight cost " + flyCost;
    }
}
