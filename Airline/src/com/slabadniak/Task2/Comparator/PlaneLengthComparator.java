package com.slabadniak.Task2.Comparator;

import com.slabadniak.Task2.Plane.Plane;
import java.util.Comparator;

public class PlaneLengthComparator implements Comparator<Plane> {
    @Override
    public int compare(Plane o1, Plane o2) {
        return Float.compare(o1.getLength(),o2.getLength());
    }
}
