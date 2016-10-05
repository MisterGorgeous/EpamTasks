package com.slabadniak.task2.plane;

import com.slabadniak.task2.exeption.UncorrectDataExeption;

public class Airliner extends Plane {
    private int passagerOnTheBoard;
    public Airliner(String name, int capacity, float tonnage, float length, float wingspan, int maxSpeed, int rangeOfFlight, int fuel) {
        setPlane(name, capacity, tonnage, length, wingspan, maxSpeed, rangeOfFlight, fuel);
    }

    public void loadPeople(int numPassagers) throws UncorrectDataExeption {
        if(numPassagers <= getCapacity() && numPassagers > 0) {
            passagerOnTheBoard = numPassagers;
        }
        throw new UncorrectDataExeption("Invalid cargo");
    }

    public void unloadPeople() {
        passagerOnTheBoard = 0;
    }
}
