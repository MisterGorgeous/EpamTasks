package com.slabadniak.stockmarket.epamstockmarket;

import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;
import com.slabadniak.stockmarket.randomevent.RandomEvent;
import com.slabadniak.stockmarket.stock.Stock;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Market implements Runnable {
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Market instance = null;
    private static Lock lock = new ReentrantLock();
    private static ArrayList<Stock> stocks;


    private Market() {
        stocks = new ArrayList<Stock>() {
            {
                add(new Stock(0, 2.45f, 3000));
                add(new Stock(1, 0.60f, 13000));
                add(new Stock(2, 1.48f, 8300));
                add(new Stock(3, 0.70f, 10000));
            }
        };

    }

    public static Market getInstance(){
        if(!isCreated.get()){
            lock.lock();
            try {
                if (!isCreated.getAndSet(true)) {
                    instance = new Market();
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Stock getCertainStock(long id /*Ticker ticker*/) throws IncorrectDataExeption{
        for (Stock stock : stocks) {
            if (stock.getId() == id) {
              return stock;
            }
        }
        throw new IncorrectDataExeption("This ticker isn't available.");
    }

    public Stock getMinPriceStock() {
        Stock stock = stocks.stream()
                .min(Comparator.comparing(Stock::getPrice))
                .get();
                return stock;
    }

    public Stock getMaxPriceStock() {
        Stock stock = stocks.stream()
                .max(Comparator.comparing(Stock::getPrice))
                .get();
        return stock;
    }

    public void run() {
        for (int i = 0; i < 10; ++i) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int index = RandomEvent.getVolatility(stocks.size());
            float price = stocks.get(index).getPrice();
            price = price * RandomEvent.getQuotation();
            stocks.get(index).setPrice(price);
            System.out.println(stocks.get(index));
        }
    }
}
