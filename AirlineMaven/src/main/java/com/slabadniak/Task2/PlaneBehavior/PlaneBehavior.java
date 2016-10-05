package com.slabadniak.task2.planebehavior;

public interface PlaneBehavior {
    boolean isPlaneFlying();
    float fly(int distance);
    void takeOff();
    void landOn();
}
