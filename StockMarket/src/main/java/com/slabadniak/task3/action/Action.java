package com.slabadniak.task3.action;

import com.slabadniak.task3.brocker.DayTrader;
import com.slabadniak.task3.brocker.Invester;
import com.slabadniak.task3.market.Market;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Action {
    public static void main(String... args) {
        Market market = Market.getInstance();
        ExecutorService executor = Executors.newFixedThreadPool(9);
        executor.submit(market);
        executor.submit(new DayTrader("Jesse Livermore", 13000));
        executor.submit(new DayTrader("William Delbert Gann", 750));
        executor.submit(new Invester("George Soros", 15000));
        executor.submit(new DayTrader("Jim Rogers", 3100));
        executor.submit(new Invester("Richard Dennis", 6400));
        executor.submit(new DayTrader("Paul Tudor Jones", 7700));
        executor.submit(new DayTrader("John Paulson", 2600));
        executor.shutdown();
    }
}
