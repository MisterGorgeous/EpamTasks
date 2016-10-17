package com.slabadniak.stockmarket.action;

import com.slabadniak.stockmarket.brocker.DayTrader;
import com.slabadniak.stockmarket.epamstockmarket.Market;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Action {
    public static void main(String... args) {
        Market market = Market.getInstance();
        ExecutorService executor = Executors.newFixedThreadPool(6);
        executor.submit(market);
        executor.submit(new DayTrader(1, 13000, market));
        executor.submit(new DayTrader(2, 12000, market));
        executor.submit(new DayTrader(3, 15000, market));
        executor.submit(new DayTrader(4, 17000, market));
        executor.submit(new DayTrader(5, 3000, market));
        executor.submit(new DayTrader(6, 7000, market));
        executor.shutdown();
        market.clean();
    }
}
