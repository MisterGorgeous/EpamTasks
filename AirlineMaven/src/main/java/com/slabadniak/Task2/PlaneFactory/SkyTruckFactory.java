package com.slabadniak.task2.planefactory;

import com.slabadniak.task2.plane.Airliner;
import com.slabadniak.task2.engine.Engine;
import com.slabadniak.task2.engine.EngineType;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.plane.SkyTruck;

import java.util.List;

import static com.slabadniak.task2.plane.PlaneAtribute.*;
import static com.slabadniak.task2.plane.PlaneAtribute.ENGINE_WEIGHT;

public class SkyTruckFactory extends PlaneFactory{
    @Override
    public Plane createPlane() {
        while(!readPlane()){
            //TO DO
        }
        Plane plane;
        List<String> atributes = getAtributes();

        plane = new SkyTruck(atributes.get(NAME.ordinal()), Integer.parseInt(atributes.get(CAPACITY.ordinal())),
                Float.parseFloat(atributes.get(TONNAGE.ordinal())),Float.parseFloat(atributes.get(LENGTH.ordinal())),
                Float.parseFloat(atributes.get(WINGSPAN.ordinal())), Integer.parseInt(atributes.get(MAXSPEED.ordinal())),
                Integer.parseInt(atributes.get(RANGE_OF_FLIGHT.ordinal())),Integer.parseInt(atributes.get(CONSUPTION_OF_FUEL.ordinal())));
        plane.setEngine(new Engine(atributes.get(ENGINE_NAME.ordinal()),  Float.parseFloat(atributes.get(ENGINE_TRUST.ordinal())),
                Integer.parseInt(atributes.get(ENGINE_WEIGHT.ordinal())), EngineType.TURBOJET));
        return plane;
    }
}
