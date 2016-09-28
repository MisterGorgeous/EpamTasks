package com.slabadniak.Task2.SkyTruck;

import com.slabadniak.Task2.Plane.Plane;
import com.slabadniak.Task2.PlaneBehavior.AirlinerBehavior.AirlinerBehavior;
import com.slabadniak.Task2.PlaneBehavior.TruckBehavior.TruckBehavior;

public class SkyTruck extends Plane implements TruckBehavior {
    private float cargoOnTheBoard;

    public SkyTruck(int capacity, float tonnage, float length, float wingspan, int maxSpeed, int rangeOfFlight, int fuel) {
        setPlane(capacity, tonnage, length, wingspan, maxSpeed, rangeOfFlight, fuel);
    }

    @Override
    public void loadCargo(float cargo) {
        if (cargo <= getTonnage() && cargo > 0) {
            cargoOnTheBoard = cargo;
            System.out.println("SkyTruck with id:" + Long.toString(getId()) + " has load " + cargoOnTheBoard + " cargo.");
        }
        //else TO Do
    }
    @Override
    public void unloadCargo(){
        System.out.println("SkyTruck  with id:" + Long.toString(getId()) + " has unload " + cargoOnTheBoard + " cargo.");
        cargoOnTheBoard = 0;
    }
}