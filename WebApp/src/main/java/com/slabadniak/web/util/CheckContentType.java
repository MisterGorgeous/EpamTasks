package com.slabadniak.web.util;

/**
 * This class is used to filter user's file format and accept only images.
 */
public class CheckContentType {
    private static final String PNG = "image/png";
    private static final String JPEG = "image/jpeg";
    private static final String JPG = "image/jpg";

    private CheckContentType(){
    }

    public static boolean isValid(String contentType){
        return contentType.equals(PNG) || contentType.equals(JPEG) || contentType.equals(JPG);
    }
}
