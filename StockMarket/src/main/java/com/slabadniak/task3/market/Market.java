package com.slabadniak.task3.market;

import com.slabadniak.task3.exeption.IncorrectDataExeption;
import com.slabadniak.task3.randomevent.RandomEvent;
import com.slabadniak.task3.stock.ProxyStock;
import com.slabadniak.task3.stock.Stock;
import com.slabadniak.task3.stock.StockIdentifier;
import com.slabadniak.task3.stock.Ticker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Market implements Runnable {
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Market instance = null;
    private static Lock lock = new ReentrantLock();
    private static ArrayList<ProxyStock> stocks = new ArrayList<>();

    private Market() {
        for (Ticker ticker : Ticker.values()) {
            stocks.add(new ProxyStock(new Stock(StockIdentifier.getNextId(), ticker.getfullName(),
                    ticker.getPrice(), ticker.getQuantity())));
        }
    }

    public static Market getInstance() {
        if (!isCreated.get()) {
            lock.lock();
            try {
                if (!isCreated.get()) {
                    instance = new Market();
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyStock getCertainStock(long id) throws IncorrectDataExeption {
        for (ProxyStock stock : stocks) {
            if (stock.getId() == id) {
                return stock;
            }
        }
        throw new IncorrectDataExeption("This ticker.txt isn't available.");
    }

    public ProxyStock getMinPriceStock() {
        ProxyStock stock = stocks.stream()
                .min(Comparator.comparing(ProxyStock::getPrice))
                .get();
        return stock;
    }

    public ProxyStock getMaxPriceStock() {
        ProxyStock stock = stocks.stream()
                .max(Comparator.comparing(ProxyStock::getPrice))
                .get();
        return stock;
    }


    public void run() {
        while (true) {
            int index = RandomEvent.getVolatility(stocks.size());
            float price = stocks.get(index).getPrice();
            price = price * RandomEvent.getQuotation();
            stocks.get(index).setPrice(price);
        }
    }
}

