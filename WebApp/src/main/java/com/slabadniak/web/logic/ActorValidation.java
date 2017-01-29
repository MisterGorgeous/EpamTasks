package com.slabadniak.web.logic;

import java.util.Arrays;

public class ActorValidation {

    public static boolean checkSizes(int ...sizes){
        int[] firstItemArray = new int[sizes.length];
        Arrays.fill(firstItemArray, sizes[0]);
        return Arrays.equals(sizes, firstItemArray);                 //check if all elements in array are equal
    }

    public static boolean checkForNull(String[] ...arrays){
        for(String[] array:arrays){
            if(array == null){
                return true;
            }
        }
        return false;
    }


}
