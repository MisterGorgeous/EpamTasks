package com.slabadniak.task2.airline;

import com.slabadniak.task2.comparator.PlaneRangeOfFlyComparator;
import com.slabadniak.task2.exeption.IncorrectDataExeption;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.planefactory.AirlinerFactory;
import com.slabadniak.task2.planefactory.SkyTruckFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class EpamAirline {
    private static EpamAirline airline;
    private ArrayList<Plane> planes;
    private final static String LOG_PATH = "src\\main\\resources\\log4j2";
    public static final Logger LOGGER = LogManager.getLogger(EpamAirline.class);

    static {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.setConfigLocation(new File(LOG_PATH).toURI());
    }

    {
        planes = new ArrayList<Plane>(){
            {
                add(new AirlinerFactory().createPlane("Boeing748"));
                add(new SkyTruckFactory().createPlane("C130"));
                add(new AirlinerFactory().createPlane("Boeing748"));
            }
        };
    }

    private EpamAirline(){
    }

    public static EpamAirline getAirline() {
        if(airline == null){
            airline = new EpamAirline();
        }
        return airline;
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    public Plane getSparePlane() throws IncorrectDataExeption {
        for(Plane plane: planes){
            if (!plane.isPlaneFlying()) {
                return plane;
            }
        }
            throw new IncorrectDataExeption("There is no available planes.All planes are flying.");
    }


    public int totalCapacity() {
        int totalCap = planes.stream()
                .mapToInt(plane -> plane.getCapacity())
                .sum();

        return totalCap;
    }

    public float totalTonnage() {
        double totalTon =  planes.stream()
                .mapToDouble(plane -> plane.getTonnage())
                .sum();

        return (float) totalTon;
    }

    public void sortByRangeOfFlying(){
        Collections.sort(planes, new PlaneRangeOfFlyComparator());
    }

    public Plane fuelConsumptionLimit(int lowValue, int highValue) throws IncorrectDataExeption {
        if(lowValue > highValue || lowValue < 0 || highValue < 0){
            throw new IncorrectDataExeption("Incorrect range.");
        }
        for(Plane plane: planes){
            if (plane.getConsumtionOfFuel() >= lowValue && plane.getConsumtionOfFuel() < highValue) {
                return plane;
            }
        }
        throw new IncorrectDataExeption("There is no plane with such parameters.");
    }
}