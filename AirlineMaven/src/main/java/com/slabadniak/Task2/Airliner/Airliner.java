package com.slabadniak.task2.airliner;

import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.planebehavior.airlinerbehavior.AirlinerBehavior;
import com.slabadniak.task2.planename.PlaneName;

public class Airliner extends Plane implements AirlinerBehavior {
    private int passagerOnTheBoard;
    public Airliner(PlaneName name, int capacity, float tonnage, float length, float wingspan, int maxSpeed, int rangeOfFlight, int fuel) {
        setPlane(name, capacity, tonnage, length, wingspan, maxSpeed, rangeOfFlight, fuel);
    }

    public void loadPeople(int numPassagers) {
        if(numPassagers <= getCapacity() && numPassagers > 0) {
            passagerOnTheBoard = numPassagers;
            System.out.println("Airliner with id:" + Long.toString(getId()) + " has load " + passagerOnTheBoard + " pasagers.");
        }
        //else TO Do
    }

    public void unloadPeople() {
        System.out.println("Airliner with id:" + Long.toString(getId()) + " has unload " + passagerOnTheBoard + " pasagers.");
        passagerOnTheBoard = 0;
    }
}
