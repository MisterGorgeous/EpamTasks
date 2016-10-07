package com.slabadniak.task2.planebehavior;

import com.slabadniak.task2.exeption.IncorrectDataExeption;

public interface PlaneBehavior {
    boolean isPlaneFlying();
    float fly(int distance) throws IncorrectDataExeption;
    void takeOff();
    void landOn();
}
