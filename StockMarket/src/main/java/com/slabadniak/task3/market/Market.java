package com.slabadniak.task3.market;

import com.slabadniak.task3.exeption.IncorrectDataExeption;
import com.slabadniak.task3.randomevent.RandomEvent;
import com.slabadniak.task3.stock.ProxyStock;
import com.slabadniak.task3.stock.Stock;
import com.slabadniak.task3.stock.StockIdGenerator;
import com.slabadniak.task3.stock.Ticker;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Market implements Runnable {
    public static final Logger LOGGER = LogManager.getLogger(Market.class);
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Market instance = null;
    private static Lock lock = new ReentrantLock();
    private static List<ProxyStock> stocks = new ArrayList<>();

    private Market() {
        for (Ticker ticker : Ticker.values()) {
            stocks.add(new ProxyStock(new Stock(StockIdGenerator.getNextId(), ticker.getfullName(),
                    ticker.getPrice(), ticker.getQuantity())));
        }
    }

    public static Market getInstance() {
        if (!isCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Market();
                    isCreated.getAndSet(true);
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
        for (int i = 0; i < 10; ++i) {
            int index = RandomEvent.getVolatility(stocks.size());
            float price = stocks.get(index).getPrice();
            price = price * RandomEvent.getQuotation();
            stocks.get(index).setPrice(price);
        }
    }
}