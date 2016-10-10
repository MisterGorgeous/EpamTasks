package com.slabadniak.stockmarket.action;

import com.slabadniak.stockmarket.brocker.Brocker;
import com.slabadniak.stockmarket.epamstockmarket.EpamStockMarket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Action {
    public static void main(String...args) {
        EpamStockMarket market = new EpamStockMarket();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Float>> list = new ArrayList<Future<Float>>();
        Future<Float> submit = executor.submit(new Brocker(1, 300, market));
        list.add(submit);
        submit = executor.submit(new Brocker(2, 1000, market));
        list.add(submit);
        submit = executor.submit(new Brocker(3, 500, market));
        list.add(submit);
    }
}
