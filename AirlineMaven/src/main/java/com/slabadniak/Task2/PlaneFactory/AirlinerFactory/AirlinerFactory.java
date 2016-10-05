package com.slabadniak.task2.planefactory.airlinerfactory;

import com.slabadniak.task2.airliner.Airliner;
import com.slabadniak.task2.engine.Engine;
import com.slabadniak.task2.engine.enginetype.EngineType;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.planefactory.PlaneFactory;
import com.slabadniak.task2.planename.PlaneName;

public class AirlinerFactory extends PlaneFactory {
    @Override
    public Plane createPlane(PlaneName name) {
        Plane plane;
        if(name == PlaneName.AH124) {
            plane = new Airliner(name, 180, 47.9f,18.0f,37.55f, 950,2650,39750);
            plane.setEngine(new Engine("НК-8", 95.00f, 2100, EngineType.TURBOFAN));
        } else {
            plane = new Airliner(name, 366, 70.6f, 17.6f, 59.6f, 955, 9800, 25552);
            plane.setEngine(new Engine("JT9D", 213.07f, 4010, EngineType.TURBOJET));
        }
        return plane;
    }
}
