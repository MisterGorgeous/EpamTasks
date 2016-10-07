package com.slabadniak.task2.plane;

import com.slabadniak.task2.exeption.IncorrectDataExeption;

public class Airliner extends Plane {
    private int passagerOnTheBoard;
    public Airliner(String name, int capacity, float tonnage, float length, float wingspan, int maxSpeed, int rangeOfFlight, int fuel) {
        setPlane(name, capacity, tonnage, length, wingspan, maxSpeed, rangeOfFlight, fuel);
    }

    public void loadPeople(int numPassagers) throws IncorrectDataExeption {
        if(numPassagers <= getCapacity() && numPassagers > 0) {
            passagerOnTheBoard = numPassagers;
            return;
        }
        throw new IncorrectDataExeption("Invalid cargo");
    }

    public void unloadPeople() {
        passagerOnTheBoard = 0;
    }
}
