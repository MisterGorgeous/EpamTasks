package com.slabadniak.Task2.PlaneFactory.AirlinerFactory;

import com.slabadniak.Task2.Airliner.Airliner;
import com.slabadniak.Task2.Engine.Engine;
import com.slabadniak.Task2.Engine.EngineType.EngineType;
import com.slabadniak.Task2.Plane.Plane;
import com.slabadniak.Task2.PlaneFactory.PlaneFactory;


public class Boeing747Factory extends PlaneFactory {
    @Override
    public Plane createPlane() {
        Plane boeing = new Airliner("Boeing-747", 366, 70.6f, 17.6f, 59.6f, 955, 9800, 25552);
        boeing.setEngine(new Engine("JT9D",213.07f, 4010, EngineType.TURBOJET));
        return boeing;
    }
}
