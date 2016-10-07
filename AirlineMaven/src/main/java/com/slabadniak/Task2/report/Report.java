package com.slabadniak.task2.report;


public class Report {

    private String  planeName;
    private int flyDistance;
    private float routeTime;
    private float flyCost;

    public Report() {}

    public Report(  String  planeName, int flyDistance, float routeTime, float flycost) {

        this.planeName = planeName;
        this.flyDistance = flyDistance;
        this.routeTime = routeTime;
        this.flyCost = flycost;
    }

    public String showInfo(){
        return "On the palne " + planeName + ".The distance was " + flyDistance + ".\n Flying time was " + routeTime
                +" hours.\nThe flight cost " + flyCost;
    }
}
