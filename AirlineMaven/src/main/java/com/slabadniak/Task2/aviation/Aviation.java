package com.slabadniak.task2.aviation;

import com.slabadniak.task2.comparator.PlaneRangeOfFlyComparator;
import com.slabadniak.task2.exeption.UncorrectDataExeption;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.planefactory.AirlinerFactory;
import com.slabadniak.task2.planefactory.SkyTruckFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Aviation {
    private ArrayList<Plane> planes;
    private final static String LOG_PATH = "src\\main\\resources\\log4j2";
    public static final Logger LOGGER = LogManager.getLogger(Aviation.class);

    static {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.setConfigLocation(new File(LOG_PATH).toURI());
    }

    public Aviation(){
        planes = new ArrayList<Plane>();
    }

    public void addPlanes(){
        planes.add(new AirlinerFactory().createPlane());
        planes.add(new AirlinerFactory().createPlane());
        planes.add(new AirlinerFactory().createPlane());
        planes.add(new SkyTruckFactory().createPlane());
        planes.add(new SkyTruckFactory().createPlane());
        planes.add(new SkyTruckFactory().createPlane());
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    public Plane getSparePlane() throws UncorrectDataExeption {
        /*Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            Plane plane = iterator.next();
            if(!plane.isPlaneFlying())
                return plane;
        }*/
        Plane sparePlane;
        sparePlane = planes.stream()
                        .filter(plane -> !plane.isPlaneFlying())
                        .findFirst().orElse(null);
        if(sparePlane == null) {
            throw new UncorrectDataExeption("There is no available planes.All planes are flying.");
        }
        return sparePlane;
    }


    public int totalCapacity() {
        int totalCap = 0;
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            totalCap += iterator.next().getCapacity();
        }
        return totalCap;
    }

    public float totalTonnage() {
        float totalTon = 0;
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            totalTon += iterator.next().getTonnage();
        }
        return totalTon;
    }

    public void sortByRangeOfFlying(){
        Collections.sort(planes, new PlaneRangeOfFlyComparator());
    }

    public Plane fuelConsumptionLimit(int lowValue, int highValue) throws UncorrectDataExeption {
        if(lowValue >= highValue || lowValue < 0 || highValue < 0){
            throw new UncorrectDataExeption("low >= high");
        }
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            Plane plane = iterator.next();
            if(plane.getConsumtionOfFuel() > lowValue && plane.getConsumtionOfFuel() < highValue)
                return plane;
        }
        throw new UncorrectDataExeption("There is no plane with such parameters.");
    }
}