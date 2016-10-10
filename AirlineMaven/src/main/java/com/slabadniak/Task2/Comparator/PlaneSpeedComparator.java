package com.slabadniak.task2.comparator;

import com.slabadniak.task2.plane.Plane;

import java.util.Comparator;

public class PlaneSpeedComparator implements Comparator<Plane> {
public int compare(Plane o1, Plane o2) {
        return Integer.compare(o1.getMaxSpeed(),o2.getMaxSpeed());
        }
}