package com.slabadniak.task2.action;

import com.slabadniak.task2.airline.EpamAirline;

public class Action {
    public static void main(String[] args) {
        EpamAirline airlines = EpamAirline.getAirline();
        airlines.totalCapacity();
        airlines.totalTonnage();
        airlines.sortByRangeOfFlying();
    }
}
