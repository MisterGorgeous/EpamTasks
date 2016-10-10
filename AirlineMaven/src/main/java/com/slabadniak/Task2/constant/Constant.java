package com.slabadniak.task2.constant;

public class Constant {
    private final static String LOG_PATH = "src/main/resources/log4j2.xml";
    private static final String AIRPORT_PATH = "src/main/resources/planes.txt";
    private static final String DEFAULT_PLANE = "BOEING747 366 69.20f  17.30f  57.90f  1024  9900  27562  JT9D 213.07f  4010";

    private Constant(){}

    public static String getLogPath() {
        return LOG_PATH;
    }

    public static String getAirportPath() {
        return AIRPORT_PATH;
    }

    public static String getDefaultPlane() {
        return DEFAULT_PLANE;
    }
}
