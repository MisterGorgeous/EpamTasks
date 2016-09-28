package com.slabadniak.Task2.PlaneFactory.SkyTruckFactory;

import com.slabadniak.Task2.Airliner.Airliner;
import com.slabadniak.Task2.Engine.Engine;
import com.slabadniak.Task2.Engine.TypeOfEngine.typeOfEngine;
import com.slabadniak.Task2.Plane.Plane;
import com.slabadniak.Task2.PlaneFactory.PlaneFactory;

public class C130Factory extends PlaneFactory {
    @Override
    public Plane createPlane() {
        Plane c130 = new Airliner(72, 180.5f,29.7f,40.1f, 645,5250,25552);
        c130.setEngine(new Engine("Allison T56", 75.00f, 880, typeOfEngine.TURBOFAN));
        return c130;
    }
}
