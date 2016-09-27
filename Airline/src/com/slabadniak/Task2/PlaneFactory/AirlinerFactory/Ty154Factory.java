package com.slabadniak.Task2.PlaneFactory.AirlinerFactory;

import com.slabadniak.Task2.Airliner.Airliner;
import com.slabadniak.Task2.Engine.Engine;
import com.slabadniak.Task2.Engine.typeOfEngine.typeOfEngine;
import com.slabadniak.Task2.Plane.Plane;
import com.slabadniak.Task2.PlaneFactory.PlaneFactory;

/**
 * Created by Siarhei on 28.09.2016.
 */
public class Ty154Factory extends PlaneFactory {
    @Override
    public Plane createPlane() {
        Plane ty = new Airliner(180, 47.9f,18.0f,37.55f, 950,2650,39750);
        ty.setEngine(new Engine("НК-8", 95.00f, 2100, typeOfEngine.TURBOFAN));
        return ty;
    }
}
