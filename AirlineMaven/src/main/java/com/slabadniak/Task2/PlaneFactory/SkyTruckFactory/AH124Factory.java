package com.slabadniak.Task2.PlaneFactory.SkyTruckFactory;

import com.slabadniak.Task2.Airliner.Airliner;
import com.slabadniak.Task2.Engine.Engine;
import com.slabadniak.Task2.Engine.EngineType.EngineType;
import com.slabadniak.Task2.Plane.Plane;
import com.slabadniak.Task2.PlaneFactory.PlaneFactory;

public class AH124Factory extends PlaneFactory{
    @Override
    public Plane createPlane() {
        Plane ah124 = new Airliner("AH-124",28, 120.0f,69.1f,73.3f, 865,4800,212350);
        ah124.setEngine(new Engine("Д-18Т", 229.85f, 4100, EngineType.TURBOPROP));
        return ah124;
    }
}
