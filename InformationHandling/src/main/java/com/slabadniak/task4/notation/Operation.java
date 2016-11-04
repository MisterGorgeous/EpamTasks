package com.slabadniak.task4.notation;

import java.util.ArrayList;

public class Operation {
   private static final ArrayList<String> operations = new ArrayList<String>() {
        {
            add(new String("++p"));
            add(new String("--p"));
            add(new String("++i"));
            add(new String("--i"));
            add(new String("("));
            add(new String(")"));
            add(new String("+"));
            add(new String("-"));
            add(new String("*"));
            add(new String("/"));
        }
    };

    public static int comparePriorities(String f, String s){
        return Integer.compare(operations.indexOf(f)/2,operations.indexOf(s)/2);
    }

     public static boolean isOperation(String symbol){
        return operations.contains(symbol);
    }

    public static boolean isIN(String symbol){
        return operations.contains(symbol) && operations.indexOf(symbol) < 4;
    }
}