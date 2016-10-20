package com.slabadniak.task3.stock;

public class StockIdentifier {
    private StockIdentifier(){}
    private static long id;
    public static long getNextId() {
        return ++id;
    }
}
