package com.slabadniak.stockmarket.stock;

import com.slabadniak.stockmarket.constant.Constant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProxyStock implements IStock {
    public static final Logger LOGGER = LogManager.getLogger(IStock.class);
    private Stock stock;
    private LinkedList<Condition> traderQueue;
    private Lock lock = new ReentrantLock();
    private Condition manager = lock.newCondition();
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
    public Stock buyStock(int quantity) {
       accessControll();
        LOGGER.log(Level.ERROR, "-------------buying stock");
        return stock.buyStock(quantity);
    }

    @Override
    public void sellStock(int quantity) {
        accessControll();
        LOGGER.log(Level.ERROR, "-------------selling stock");
        stock.sellStock(quantity);
    }

    @Override
    public void setPrice(float price) {
        accessControll();
        LOGGER.log(Level.ERROR, "-------------setting price");
        stock.setPrice(price);
    }

  private void accessControll(){
      lock.lock();
      try {
          Condition traderwork = lock.newCondition();
          traderQueue.addLast(traderwork);
          LOGGER.log(Level.ERROR, "--------added to Queue.");
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


    public void stopQueueManager(){
        queueManager.interrupt();
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
                        LOGGER.log(Level.ERROR, "----------Notify trader " + traderQueue.size());

                } catch (InterruptedException e) {
                    LOGGER.log(Level.ERROR, e);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}


