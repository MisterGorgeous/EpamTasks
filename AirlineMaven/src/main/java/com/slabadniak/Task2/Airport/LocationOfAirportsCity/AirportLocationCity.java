package com.slabadniak.Task2.Airport.LocationOfAirportsCity;

import com.slabadniak.Task2.Exeption.InvalidArgumentExeption;

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
