package com.slabadniak.stockmarket.epamstockmarket;

import com.slabadniak.stockmarket.constant.Constant;
import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;
import com.slabadniak.stockmarket.randomevent.RandomEvent;
import com.slabadniak.stockmarket.stock.ProxyStock;
import com.slabadniak.stockmarket.stock.Stock;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Market implements Runnable {
    public static final Logger LOGGER = LogManager.getLogger(Market.class);
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Market instance = null;
    private static Lock lock = new ReentrantLock();
    private static ArrayList<ProxyStock> stocks;
    static {
        LoggerContext context = (LoggerContext) LogManager.getContext(true);
        context.setConfigLocation(new File(Constant.LOG_PATH).toURI());
    }

    private Market() {
        stocks = new ArrayList<ProxyStock>() {
            {
                add(new ProxyStock(new Stock(0, 78.49f, 10000)));
              /*  add(new Stock(1, 117.64f, 100000));
                add(new Stock(2, 127.87f, 100000));
                add(new Stock(3, 196.71f, 100000));
                add(new Stock(4, 44.88f, 100000));
                add(new Stock(5, 41.67f, 100000));
                add(new Stock(6, 82.58f, 100000));
                add(new Stock(7, 91.41f, 100000));
                add(new Stock(8, 37.45f, 100000));*/
            }
        };

    }

    public static Market getInstance(){
        if(!isCreated.get()){
            lock.lock();
            try {
                if (!isCreated.get()) {
                    instance = new Market();
                    isCreated.set(true);
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyStock getCertainStock(long id /*Ticker ticker*/) throws IncorrectDataExeption{
        for (ProxyStock stock : stocks) {
            if (stock.getId() == id) {
              return stock;
            }
        }
        throw new IncorrectDataExeption("This ticker isn't available.");
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
            try {
                sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int index = RandomEvent.getVolatility(stocks.size());
         //   LOGGER.log(Level.INFO,stocks.get(index) + " -- was");
           // System.out.println(stocks.get(index)+ " -- was");
            float price = stocks.get(index).getPrice();
            price = price * RandomEvent.getQuotation();
           // stocks.get(index).setPrice(price);
            //LOGGER.log(Level.DEBUG,stocks.get(index) + " -- now");
          //  System.out.println(stocks.get(index)+ " -- now");
        }
    }
}
