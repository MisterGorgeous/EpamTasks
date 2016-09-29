package com.slabadniak.Task2.Airport.ClassOfAirport;

public enum classOfAirport {
    I, //100000—70000 passegers a year
    II, //70000—40000
    III, //40000—20000
    IV, //20000—5000
    V ;//5000—1000
    public static classOfAirport getAirport(String num){
        return classOfAirport.valueOf(num);
    }
}
