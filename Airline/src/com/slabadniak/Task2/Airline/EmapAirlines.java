package com.slabadniak.Task2.Airline;

import com.slabadniak.Task2.Comparator.PlaneRangeOfFlyComparator;
import com.slabadniak.Task2.Plane.Plane;

import java.util.*;

public class EmapAirlines {
    private ArrayList<Plane> planes;

    public EmapAirlines(){
        planes = new ArrayList<Plane>();
    }

    public void addPlane(Plane plane){
        planes.add(plane);
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    public int totalCapacity() {
        int totalCap = 0;
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            totalCap += iterator.next().getCapacity();
        }
        return totalCap;
    }

    public float totalTonnage() {
        float totalTon = 0;
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            totalTon += iterator.next().getTonnage();
        }
        return totalTon;
    }

    public void sortByRangeOfFlying(){
        Collections.sort(planes, new PlaneRangeOfFlyComparator());
    }

    public Plane fuelConsumptionLimit(int lowValue, int highValue){
        if(lowValue >= highValue){
            //throw InvalidArgumentExeption("low >= high');
        }
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            Plane plane = iterator.next();
            if(plane.getConsumtionOfFuel() > lowValue && plane.getConsumtionOfFuel() < highValue)
                return plane;
        }
        return null;//TO Do
    }


}
