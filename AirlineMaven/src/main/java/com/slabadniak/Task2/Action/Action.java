package com.slabadniak.task2.action;

import com.slabadniak.task2.airline.EpamAirline;


public class Action {
    public static void main(String[] args){
        String  workingDir = System.getProperty("user.dir");
        System.out.println((new java.io.File(".")).getAbsolutePath());
        EpamAirline airlines = EpamAirline.getAirline();
        System.out.println(airlines.getPlanes());
        System.out.println("Total Capacity" + airlines.totalCapacity());
        System.out.println("Total Tonnage" + airlines.totalTonnage());
        airlines.sortByRangeOfFlying();
        airlines.getPlanes();

    }
}
