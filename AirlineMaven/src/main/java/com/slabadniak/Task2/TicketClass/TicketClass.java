package com.slabadniak.task2.ticketclass;

public enum TicketClass {
    FIRST,
    BUSINESS,
    ECONOMY;

    public static float getClassCoefitient(TicketClass ticketClass){
        if(ticketClass.equals(FIRST)) {
            return 1.75f;
        } else if(ticketClass.equals(BUSINESS)){
            return 1.25f;
        } else {
            return 1.00f;
        }
    }
}
