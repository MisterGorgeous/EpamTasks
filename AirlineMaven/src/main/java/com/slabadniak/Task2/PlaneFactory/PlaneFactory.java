package com.slabadniak.task2.planefactory;

import com.slabadniak.task2.plane.Plane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class PlaneFactory {
    private static final String AIRPORT_PATH = "src\\main\\resources\\Planes";
    private List<String> atributes;

    public abstract Plane createPlane();

    public boolean readPlane(){
        atributes = new ArrayList<>();
        Stream<String> stream = null;
        try {
            stream = Files.lines(Paths.get(AIRPORT_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        atributes = stream.collect(Collectors.toList());
        return true;
    }

    public List<String> getAtributes() {
        return atributes;
    }
}
