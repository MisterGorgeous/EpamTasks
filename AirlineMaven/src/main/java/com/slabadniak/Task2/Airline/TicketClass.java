package com.slabadniak.task2.airline;

public enum TicketClass {
    FIRST,
    BUSINESS,
    ECONOMY;
    private static final float FIRST_COEFFICIENT = 1.75f;
    private static final float BUSINESS_COEFFICIENT = 1.25f;
    private static final float ECONOMY_COFFICIENT = 1.00f;

    public static float getClassCoefitient(TicketClass ticketClass){
        if(ticketClass.equals(FIRST)) {
            return FIRST_COEFFICIENT;
        } else if(ticketClass.equals(BUSINESS)){
            return BUSINESS_COEFFICIENT;
        } else {
            return ECONOMY_COFFICIENT;
        }
    }
}
