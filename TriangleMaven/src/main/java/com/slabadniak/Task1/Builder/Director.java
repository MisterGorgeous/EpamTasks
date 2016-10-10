package com.slabadniak.Task1.Builder;

import com.slabadniak.Task1.Triangle.Triangle;

import java.util.ArrayList;
import java.util.TreeMap;

public class Director {
    public static ArrayList<Triangle> createTriangles(BaseBuilder builder) {
        builder.buildTriangles();
        return builder.getTriangles();
    }
}
