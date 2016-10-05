package com.slabadniak.task2.planefactory;

import com.slabadniak.task2.engine.enginetype.EngineType;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.planename.PlaneName;

public abstract class PlaneFactory {
    public abstract Plane createPlane(PlaneName name);
}
