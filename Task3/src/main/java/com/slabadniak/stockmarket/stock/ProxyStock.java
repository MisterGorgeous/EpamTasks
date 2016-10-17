package com.slabadniak.stockmarket.stock;

import com.slabadniak.stockmarket.brocker.Trader;
import com.slabadniak.stockmarket.constant.Constant;
import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProxyStock implements IStock {
    private Stock stock;
    private LinkedList<Condition> traderQueue;
    private Lock lock = new ReentrantLock();
    private Condition managerwork = lock.newCondition();
    private QueueManager queueManager;

    static {
        LoggerContext context = (LoggerContext) LogManager.getContext(true);
        context.setConfigLocation(new File(Constant.LOG_PATH).toURI());
    }
    public ProxyStock(Stock stock) {
        this.stock = stock;
        traderQueue = new LinkedList<>();
        queueManager = new QueueManager();
        queueManager.start();
    }

    @Override
    public Stock buyStock(int quantity, Trader trader) throws IncorrectDataExeption {
        lock.lock();
        try {
            Condition traderwork = lock.newCondition();
            traderQueue.addLast(traderwork);
          //  System.out.println(trader.toString() + "added to Queue to buy.");
          //  System.out.println(traderQueue);
            managerwork.signal();
            traderwork.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
       // System.out.println(trader.toString() + "buying stock");
        return stock.buyStock(quantity, trader);
    }

    @Override
    public void sellStock(int quantity, Trader trader) {
        lock.lock();
        try {
            Condition traderwork = lock.newCondition();
            traderQueue.addLast(traderwork);
           // System.out.println(trader.toString() + "added to Queue to sell.");
            managerwork.signal();
            traderwork.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        //System.out.println(trader.toString() + "selling stock");
        stock.sellStock(quantity, trader);
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
    public void setPrice(float price) {
        //TO DO
        stock.setPrice(price);
    }

    @Override
    public Stock copyStock() {
        return stock.copyStock();
    }

    public void stopQueueManager(){
        queueManager.interrupt();
    }

    private class QueueManager extends Thread {
        @Override
        public void run() {
            while (true) {
               // System.out.println("Wait");
                lock.lock();
                try {
                    while (traderQueue.isEmpty()) {
                        managerwork.await();
                    }
                    Condition traderwork = traderQueue.removeFirst();
                       // System.out.println("Get trader");
                        traderwork.signal();
                      //  System.out.println("Notify trader");

                } catch (InterruptedException e) {
                    LOGGER.log(Level.INFO, "Cleaned." + e);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}


