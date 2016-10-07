package com.slabadniak.task2.plane;

import com.slabadniak.task2.exeption.IncorrectDataExeption;

public class SkyTruck extends Plane {
    private float cargoOnTheBoard;

    public SkyTruck(String name, int capacity, float tonnage, float length, float wingspan, int maxSpeed, int rangeOfFlight, int fuel) {
        setPlane(name, capacity, tonnage, length, wingspan, maxSpeed, rangeOfFlight, fuel);
    }

    public void loadCargo(float cargo) throws IncorrectDataExeption {
        if (cargo <= getTonnage() && cargo > 0) {
            cargoOnTheBoard = cargo;
            return;
        }
        throw new IncorrectDataExeption("Invalid cargo");
    }

    public void unloadCargo() {
        cargoOnTheBoard = 0;
    }
}