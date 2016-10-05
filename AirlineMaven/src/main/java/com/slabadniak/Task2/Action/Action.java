package com.slabadniak.task2.action;

import com.slabadniak.task2.airline.EpamAirlines;
import com.slabadniak.task2.airport.airportlocation.AirportLocationCity;
import com.slabadniak.task2.exeption.InvalidArgumentExeption;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.report.Report;
import com.slabadniak.task2.ticketclass.TicketClass;

public class Action {
    public static void main(String[] args){
        EpamAirlines airlines = EpamAirlines.getAirline();

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
            Report report = airlines.getAiroports().flyFromTo(AirportLocationCity.ISTANBUL, AirportLocationCity.MINSK, TicketClass.BUSINESS);
    } catch (InvalidArgumentExeption invalidArgumentExeption) {
        invalidArgumentExeption.printStackTrace();
    }
    }
}
