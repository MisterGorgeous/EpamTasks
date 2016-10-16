package com.slabadniak.stockmarket.randomevent;

import java.util.Random;

public class RandomEvent {
    private static Random randomGenerator = new Random();

    public static int getVolatility(int range) {
        return randomGenerator.nextInt(range);
    }

    public static float getQuotation() {
        return 1.00f + (randomGenerator.nextFloat() - 0.50f) / 5;
    }
}
