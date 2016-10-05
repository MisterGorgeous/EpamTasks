package com.slabadniak.task2.action;

import com.slabadniak.task2.airline.EpamAirlines;
import com.slabadniak.task2.airport.AirportLocationCity;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.report.Report;
import com.slabadniak.task2.airline.TicketClass;

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
            System.out.println(plane.getConsumtionOfFuel());
        }
            Report report = airlines.getAiroports().flyFromTo(AirportLocationCity.ISTANBUL, AirportLocationCity.MINSK, TicketClass.BUSINESS);
            System.out.print(report.showInfo());
    }
}
