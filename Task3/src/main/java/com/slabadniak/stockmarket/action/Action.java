package com.slabadniak.stockmarket.action;

import com.slabadniak.stockmarket.brocker.DayTrader;
import com.slabadniak.stockmarket.epamstockmarket.Market;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Action {
    public static void main(String... args) {
        Market market = Market.getInstance();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //  List<Future<Float>> list = new ArrayList<Future<Float>>();
        //Future<Float> submit =
        //list.add(submit);

        executor.submit(market);
        executor.submit(new DayTrader(1, 300, market));
        executor.submit(new DayTrader(2, 200, market));
        executor.submit(new DayTrader(3, 500, market));
        executor.submit(new DayTrader(4, 700, market));
        executor.shutdown();
    }
}
