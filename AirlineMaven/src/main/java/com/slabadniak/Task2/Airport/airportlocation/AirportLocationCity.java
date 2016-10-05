package com.slabadniak.task2.airport.airportlocation;

import com.slabadniak.task2.exeption.InvalidArgumentExeption;

public enum AirportLocationCity {
    LONDON,
    PARIS,
    AMSTERDAM,
    ISTANBUL,
    BERLIN,
    MOSCOW,
    HELSINKI,
    WARSAW,
    RIGA,
    KYIV,
    MINSK;
    public static AirportLocationCity getLocation(String num) throws InvalidArgumentExeption {
        return AirportLocationCity.valueOf(num.toUpperCase());
    }
}
