package com.slabadniak.task2.aviation;

import com.slabadniak.task2.comparator.PlaneRangeOfFlyComparator;
import com.slabadniak.task2.exeption.InvalidArgumentExeption;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.planefactory.airlinerfactory.AirlinerFactory;
import com.slabadniak.task2.planefactory.skytruckfactory.SkyTruckFactory;
import com.slabadniak.task2.planename.PlaneName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Aviation {
    private ArrayList<Plane> planes;
    private final static String LOG_PATH = "src\\main\\java\\com\\slabadniak\\tasm\\File\\";
    public static final Logger LOGGER = LogManager.getLogger(Aviation.class);

    static {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.setConfigLocation(new File(LOG_PATH).toURI());
    }

    public Aviation(){
        planes = new ArrayList<Plane>();
        LOGGER.log(Level.DEBUG, "Check.");
    }

    public void addPlanes(){
        planes.add(new AirlinerFactory().createPlane(PlaneName.BOEING747));
        planes.add(new AirlinerFactory().createPlane(PlaneName.BOEING747));
        planes.add(new AirlinerFactory().createPlane(PlaneName.TY154));
        planes.add(new SkyTruckFactory().createPlane(PlaneName.C130));
        planes.add(new SkyTruckFactory().createPlane(PlaneName.C130));
        planes.add(new SkyTruckFactory().createPlane(PlaneName.AH124));
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    public Plane getSparePlane() throws InvalidArgumentExeption {
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            Plane plane = iterator.next();
            if(!plane.isPlaneFlying())
                return plane;
        }

        throw new InvalidArgumentExeption("There is no available planes.All planes are flying.");
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

 /*   public <T extends Number> T totalValue(planeAtribute atribute){
        T result;
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            result += iterator.next().getAtribute(atribute).;
        }
        return result;
    }*/

    public void sortByRangeOfFlying(){
        Collections.sort(planes, new PlaneRangeOfFlyComparator());
    }

    public Plane fuelConsumptionLimit(int lowValue, int highValue) throws InvalidArgumentExeption {
        if(lowValue >= highValue || lowValue < 0 || highValue < 0){
            throw new InvalidArgumentExeption("low >= high");
        }
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            Plane plane = iterator.next();
            if(plane.getConsumtionOfFuel() > lowValue && plane.getConsumtionOfFuel() < highValue)
                return plane;
        }
        throw new InvalidArgumentExeption("There is no plane with such parameters.");
    }
}