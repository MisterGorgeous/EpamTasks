package com.slabadniak.Task2.Engine;

import com.slabadniak.Task2.Engine.TypeOfEngine.typeOfEngine;

public class Engine {
    private String name;
    private float thrust; //kH
    private int weight; //kg
    private typeOfEngine engineType;

    public Engine(String name,float thrust, int weight,typeOfEngine engineType){
        this.name = name;
        this.thrust = thrust;
        this.weight = weight;
        this.engineType = engineType;
    }
}

