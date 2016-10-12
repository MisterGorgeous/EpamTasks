package com.slabadniak.task2.airline;

import com.slabadniak.task2.algoritm.QuickSort;
import com.slabadniak.task2.comparator.PlaneRangeOfFlyComparator;
import com.slabadniak.task2.exeption.IncorrectDataExeption;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.planefactory.AirlinerFactory;
import com.slabadniak.task2.planefactory.SkyTruckFactory;

import java.util.ArrayList;


public class EpamAirline {
    private static EpamAirline airline;
    private ArrayList<Plane> planes;

    {
        planes = new ArrayList<Plane>() {
            {
                add(new AirlinerFactory().createPlane("Boeing748"));
                add(new SkyTruckFactory().createPlane("C130"));
                add(new AirlinerFactory().createPlane("A380"));
                add(new AirlinerFactory().createPlane("Boeing777"));
                add(new AirlinerFactory().createPlane("Boeing747"));
                add(new AirlinerFactory().createPlane("A320"));
                add(new SkyTruckFactory().createPlane("AH124"));
                add(new AirlinerFactory().createPlane("A380"));
            }
        };
    }

    private EpamAirline() {
    }

    public static EpamAirline getAirline() {
        if (airline == null) {
            airline = new EpamAirline();
        }
        return airline;
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    public <T extends Plane> void addPlane(T plane) {
        planes.add(plane);
    }

    public int totalCapacity() {
        int totalCap = planes.stream()
                .mapToInt(plane -> plane.getCapacity())
                .sum();
        return totalCap;
    }

    public float totalTonnage() {
        double totalTon = planes.stream()
                .mapToDouble(plane -> plane.getTonnage())
                .sum();
        return (float) totalTon;
    }

    public void sortByRangeOfFlying() {
        QuickSort.sort(planes, new PlaneRangeOfFlyComparator());
    }

    public Plane fuelConsumptionLimit(float lowValue, float highValue) throws IncorrectDataExeption {
        if (lowValue > highValue || lowValue < 0 || highValue < 0) {
            throw new IncorrectDataExeption("Incorrect range.");
        }
        Plane plane = planes.stream()
                .filter(p -> (p.getConsumtionOfFuel() >= lowValue) && (p.getConsumtionOfFuel() < highValue))
                .findFirst().orElse(null);
        if (plane == null) {
            throw new IncorrectDataExeption("There is no plane with such parameters.");
        }
        return plane;
    }
}