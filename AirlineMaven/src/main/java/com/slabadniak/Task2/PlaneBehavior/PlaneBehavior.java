package com.slabadniak.Task2.PlaneBehavior;

public interface PlaneBehavior {
    boolean isPlaneFlying();
    float fly(int distance);
    void takeOff();
    void landOn();
}
