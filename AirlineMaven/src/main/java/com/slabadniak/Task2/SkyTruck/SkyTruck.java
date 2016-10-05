package com.slabadniak.task2.skytruck;

import com.slabadniak.task2.exeption.InvalidArgumentExeption;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.planebehavior.truckbehavior.TruckBehavior;
import com.slabadniak.task2.planename.PlaneName;

public class SkyTruck extends Plane implements TruckBehavior {
    private float cargoOnTheBoard;

    public SkyTruck(PlaneName name, int capacity, float tonnage, float length, float wingspan, int maxSpeed, int rangeOfFlight, int fuel) {
        setPlane(name, capacity, tonnage, length, wingspan, maxSpeed, rangeOfFlight, fuel);
    }

    public void loadCargo(float cargo) throws InvalidArgumentExeption {
        if (cargo <= getTonnage() && cargo > 0) {
            cargoOnTheBoard = cargo;
            System.out.println("SkyTruck with id:" + Long.toString(getId()) + " has load " + cargoOnTheBoard + " cargo.");
        }
        throw new InvalidArgumentExeption("Invalid cargo");
    }

    public void unloadCargo(){
        System.out.println("SkyTruck  with id:" + Long.toString(getId()) + " has unload " + cargoOnTheBoard + " cargo.");
        cargoOnTheBoard = 0;
    }
}