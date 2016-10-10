package com.slabadniak.task2.comparator;

import com.slabadniak.task2.plane.Plane;
import java.util.Comparator;

public class PlaneLengthComparator implements Comparator<Plane>{
    public int compare(Plane o1, Plane o2) {
        return Float.compare(o1.getLength(),o2.getLength());
    }
}
