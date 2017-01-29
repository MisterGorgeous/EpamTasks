package com.slabadniak.web.service;

import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.exeption.ServiceExeption;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortMovies {
    private static final String ALPABETIC = "Alphabetic";
    private static final String DECREASE= "Start with highest rate";
    private static final String INCREASE = "Start with lowest rate";

    public static String sort(List<Movie> movies, String order, String attribute) throws ServiceExeption {
        if(checkNecessity(order,attribute)) {
            String newAttribute;
            switch (order) {
                case ALPABETIC:
                    Collections.sort(movies, Comparator.comparing(Movie::getTitle));
                    newAttribute = ALPABETIC;
                    break;
                case DECREASE:
                    Collections.sort(movies, Comparator.comparing(Movie::getRating));
                    Collections.reverse(movies);
                    newAttribute = DECREASE;
                    break;
                default:
                    Collections.sort(movies, Comparator.comparing(Movie::getRating));
                    newAttribute = INCREASE;
                    break;
            }
            return newAttribute;
        }else {
            return "";
        }
    }

    static public boolean checkNecessity(String order,String attribute){
        if(order == null || order.isEmpty()){
            return false;
        }
        if(order.equals(attribute)){
            return false;
        }
        return true;
    }
}
