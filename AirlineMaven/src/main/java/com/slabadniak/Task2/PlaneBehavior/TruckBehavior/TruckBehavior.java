package com.slabadniak.task2.planebehavior.truckbehavior;

import com.slabadniak.task2.exeption.InvalidArgumentExeption;

public interface TruckBehavior {
    void loadCargo(float cargo) throws InvalidArgumentExeption;
    void unloadCargo();
}
