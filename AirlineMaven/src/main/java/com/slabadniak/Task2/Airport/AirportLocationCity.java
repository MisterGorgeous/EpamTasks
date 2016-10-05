package com.slabadniak.task2.airport;

import com.slabadniak.task2.exeption.UncorrectDataExeption;

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
    public static AirportLocationCity getLocation(String num) throws UncorrectDataExeption {
        return AirportLocationCity.valueOf(num.toUpperCase());
    }
}
