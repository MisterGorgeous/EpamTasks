package com.slabadniak.stockmarket.epamstockmarket;

import com.slabadniak.stockmarket.stock.Stock;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Market implements Runnable {
    private ArrayList<Stock> stocks;

    public Market() {
        stocks = new ArrayList<Stock>() {
            {
                add(new Stock(0, 2.45f, 3000));
                add(new Stock(1, 0.60f, 13000));
                add(new Stock(2, 1.48f, 8300));
                add(new Stock(3, 0.70f, 10000));
            }
        };

    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public void run() {

        for (int i = 0; i < 10; ++i) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Random randomGenerator = new Random();
            int random = randomGenerator.nextInt(stocks.size());
            float price = stocks.get(random).getPrice();
            price += price * 0.15;
            stocks.get(random).setPrice(price);
            System.out.println(stocks.get(random));
        }
    }
}
