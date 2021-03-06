package com.slabadniak.task2.datachecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataChecker {
    //check the hole string from file
    private static final String PLANE_REGEX = "^[A-Z]{1,24}[\\d]{0,8}[ ][\\d]{1,4}[ ]([\\d]{1,6}[\\.][\\d]{1,2}[f][ ]){3}" +
            "([\\d]{1,8}[ ]){3}[\\w]{1,32}[ ][\\d]{1,6}[\\.][\\d]{1,2}[f][ ][\\d]{1,8}[ ]*$";

    public static boolean checkPlane(String planeData) {
        Pattern p = Pattern.compile(PLANE_REGEX);
        Matcher m = p.matcher(planeData);
        return m.matches();
    }
}