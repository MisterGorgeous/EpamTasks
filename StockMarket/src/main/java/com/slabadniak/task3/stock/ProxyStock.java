package com.slabadniak.task3.stock;

import com.slabadniak.task3.constant.Constant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProxyStock implements IStock {
    static final Logger LOGGER = LogManager.getLogger(ProxyStock.class);
    private Stock stock;
    private Deque<Condition> traderQueue;
    private Lock lock = new ReentrantLock();
    private Condition manager = lock.newCondition();
    private QueueManager queueManager;

    static {
        LoggerContext context = (LoggerContext) LogManager.getContext(true);
        context.setConfigLocation(new File(Constant.LOGGER).toURI());
    }

    public ProxyStock(Stock stock) {
        this.stock = stock;
        traderQueue = new LinkedList<>();
        queueManager = new QueueManager();
        queueManager.start();
    }

    @Override
    public Stock buyStock(int quantity) {
        accessControll();
        return stock.buyStock(quantity);
    }

    @Override
    public void sellStock(int quantity) {
        accessControll();
        stock.sellStock(quantity);
    }

    @Override
    public void setPrice(float price) {
        accessControll();
        stock.setPrice(price);
    }

    private void accessControll() {
        lock.lock();
        try {
            Condition traderwork = lock.newCondition();
            traderQueue.add(traderwork);
            manager.signal();
            traderwork.await();
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public float getPrice() {
        return stock.getPrice();
    }

    @Override
    public long getId() {
        return stock.getId();
    }

    @Override
    public int getQuantity() {
        return stock.getQuantity();
    }

    @Override
    public String toString() {
        return stock.toString();
    }

    private class QueueManager extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (traderQueue.isEmpty()) {
                        manager.await();
                    }
                    Condition traderwork = traderQueue.removeFirst();
                    traderwork.signal();
                } catch (InterruptedException e) {
                    LOGGER.log(Level.ERROR, e);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}


