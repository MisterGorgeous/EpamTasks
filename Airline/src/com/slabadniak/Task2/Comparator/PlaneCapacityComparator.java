package com.slabadniak.Task2.Comparator;

import com.slabadniak.Task2.Plane.Plane;
import java.util.Comparator;

public class PlaneCapacityComparator implements Comparator<Plane>{
    @Override
    public int compare(Plane o1, Plane o2) {
        return Integer.compare(o1.getCapacity(),o2.getCapacity());
    }
}
