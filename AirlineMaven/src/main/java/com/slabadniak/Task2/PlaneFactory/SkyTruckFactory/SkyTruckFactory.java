package com.slabadniak.task2.planefactory.skytruckfactory;

import com.slabadniak.task2.airliner.Airliner;
import com.slabadniak.task2.engine.Engine;
import com.slabadniak.task2.engine.enginetype.EngineType;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.planefactory.PlaneFactory;
import com.slabadniak.task2.planename.PlaneName;

public class SkyTruckFactory extends PlaneFactory{
    @Override
    public Plane createPlane(PlaneName name) {
        Plane plane;
        if(name == PlaneName.AH124) {
            plane = new Airliner(name, 28, 120.0f, 69.1f, 73.3f, 865, 4800, 212350);
            plane.setEngine(new Engine("Д-18Т", 229.85f, 4100, EngineType.TURBOPROP));
        } else {
            plane = new Airliner(name, 72, 180.5f, 29.7f, 40.1f, 645, 5250, 25552);
            plane.setEngine(new Engine("Allison T56", 75.00f, 880, EngineType.TURBOFAN));
        }
        return plane;
    }
}
