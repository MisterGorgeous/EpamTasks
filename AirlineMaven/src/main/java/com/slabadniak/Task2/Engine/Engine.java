package com.slabadniak.task2.engine;

import com.slabadniak.task2.engine.enginetype.EngineType;

public class Engine {
    private String name;
    private float thrust;            //kH
    private int weight;              //kg
    private EngineType engineType;
    private boolean working;

    public Engine(String name,float thrust, int weight,EngineType engineType){
        this.name = name;
        this.thrust = thrust;
        this.weight = weight;
        this.engineType = engineType;
        this.working = false;
    }

    public void startEngine(){
        working = true;
    }

    public void stopEngine(){
        working = false;
    }

    public boolean isEngineWorking() {
        return working;
    }
}

