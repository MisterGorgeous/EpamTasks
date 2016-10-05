package com.slabadniak.task2.comparator;

import com.slabadniak.task2.plane.Plane;

import java.util.Comparator;

public class PlaneTonnageComparator implements Comparator<Plane> {
public int compare(Plane o1, Plane o2) {
        return Float.compare(o1.getTonnage(),o2.getTonnage());
        }
}