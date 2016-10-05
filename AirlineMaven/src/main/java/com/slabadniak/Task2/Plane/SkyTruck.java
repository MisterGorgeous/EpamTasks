package com.slabadniak.task2.plane;

import com.slabadniak.task2.exeption.UncorrectDataExeption;

public class SkyTruck extends Plane {
    private float cargoOnTheBoard;

    public SkyTruck(String name, int capacity, float tonnage, float length, float wingspan, int maxSpeed, int rangeOfFlight, int fuel) {
        setPlane(name, capacity, tonnage, length, wingspan, maxSpeed, rangeOfFlight, fuel);
    }

    public void loadCargo(float cargo) throws UncorrectDataExeption {
        if (cargo <= getTonnage() && cargo > 0) {
            cargoOnTheBoard = cargo;
        }
        throw new UncorrectDataExeption("Invalid cargo");
    }

    public void unloadCargo() {
        cargoOnTheBoard = 0;
    }
}