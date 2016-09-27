package com.slabadniak.Task2.Action;

import com.slabadniak.Task2.Airline.EmapAirlines;
import com.slabadniak.Task2.Plane.Plane;
import com.slabadniak.Task2.PlaneFactory.AirlinerFactory.Boeing747Factory;
import com.slabadniak.Task2.PlaneFactory.AirlinerFactory.Ty154Factory;
import com.slabadniak.Task2.PlaneFactory.SkyTruckFactory.AH124Factory;
import com.slabadniak.Task2.PlaneFactory.SkyTruckFactory.C130Factory;

public class Action {
    public static void main(String[] args){
        EmapAirlines airlines = new EmapAirlines();
        airlines.addPlane(new Boeing747Factory().createPlane());
        airlines.addPlane(new Ty154Factory().createPlane());
        airlines.addPlane(new AH124Factory().createPlane());
        airlines.addPlane( new C130Factory().createPlane());
        System.out.println(airlines.getPlanes());
        System.out.println("Total Capacity" + airlines.totalCapacity());
        System.out.println("Total Tonnage" + airlines.totalTonnage());
        System.out.println("Total Tonnage" + airlines.totalTonnage());
        airlines.sortByRangeOfFlying();
        System.out.println(airlines.getPlanes());
        for(Plane plane: airlines.getPlanes()){
            System.out.println(plane.getConsumtionOfFuel());
        }

    }
}
