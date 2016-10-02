package com.slabadniak.Task2.Action;

import com.slabadniak.Task2.Airline.EpamAirlines;
import com.slabadniak.Task2.Airport.LocationOfAirportsCity.AirportLocationCity;
import com.slabadniak.Task2.Exeption.InvalidArgumentExeption;
import com.slabadniak.Task2.Plane.Plane;
import com.slabadniak.Task2.TicketClass.TicketClass;

public class Action {
    public static void main(String[] args){
        EpamAirlines airlines = new EpamAirlines();

        System.out.println(airlines.getAviation().getPlanes());
        System.out.println("Total Capacity" + airlines.getAviation().totalCapacity());
        System.out.println("Total Tonnage" + airlines.getAviation().totalTonnage());
        System.out.println("Total Tonnage" + airlines.getAviation().totalTonnage());
        airlines.getAviation().sortByRangeOfFlying();
        System.out.println(airlines.getAviation().getPlanes());
        for(Plane plane: airlines.getAviation().getPlanes()){
            //System.out.println(plane.getAtribute(planeAtribute.CONSUPTION_OF_FUEL).doubleValue());
            System.out.println(plane.getConsumtionOfFuel());
        }
        try{
            System.out.println(airlines.getAiroports().flyFromTo(AirportLocationCity.ISTANBUL, AirportLocationCity.MINSK, TicketClass.BUSINESS));
    } catch (InvalidArgumentExeption invalidArgumentExeption) {
        invalidArgumentExeption.printStackTrace();
    }
    }
}
