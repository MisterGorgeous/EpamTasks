package com.slabadniak.web.util;

import java.util.Arrays;


/**
 * This utils class is used to validate actors, whose has been added to db.
 */
public class ActorValidation {

    public static boolean checkSizes(int ...sizes){
        int[] firstItemArray = new int[sizes.length];
        Arrays.fill(firstItemArray, sizes[0]);
        return Arrays.equals(sizes, firstItemArray);                 //check if all elements in array are equal
    }

    public static boolean checkForNull(String[] ...arrays){
        for(String[] array:arrays){                                  //check if there is any nulls
            if(array == null){
                return true;
            }
        }
        return false;
    }


}
