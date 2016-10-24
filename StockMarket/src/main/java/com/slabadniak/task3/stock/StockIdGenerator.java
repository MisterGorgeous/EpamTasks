package com.slabadniak.task3.stock;

public class StockIdGenerator {
    private StockIdGenerator(){}
    private static long id;
    public static long getNextId() {
        return ++id;
    }
}
