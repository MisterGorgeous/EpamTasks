package com.slabadniak.stockmarket.stock;

import com.slabadniak.stockmarket.brocker.Trader;
import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProxyStock implements IStock{
    private Stock stock;
    private LinkedList<Trader> traderQueue;
   // private Lock lock = new ReentrantLock();
   // private Lock lock1 = new ReentrantLock();
    private QueueManager queueManager;

    public ProxyStock(Stock stock) {
        this.stock = stock;
        traderQueue = new LinkedList<>();
        queueManager = new QueueManager();
        queueManager.start();
    }

    @Override
    public Stock buyStock(int quantity,Trader trader) throws IncorrectDataExeption {
        //lock.lock();
        synchronized (traderQueue) {
            traderQueue.addLast(trader);
            /*System.out.println(trader.toString() + "added to Queue to buy.");
            System.out.println(traderQueue);*/
            traderQueue.notify();
        }
        synchronized (trader){
            try {
                trader.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //lock.unlock();
            }
        }
        System.out.println(trader.toString() + "buying stock");
        return stock.buyStock(quantity, trader);
    }

    @Override
    public void sellStock(int quantity, Trader trader) {
       // lock.lock();
        synchronized (traderQueue) {
            traderQueue.addLast(trader);
       //     System.out.println(trader.toString() + "added to Queue to sell.");
            traderQueue.notify();
        }
        synchronized (trader){
            try {
                trader.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //lock.unlock();
            }
        }
        System.out.println(trader.toString() + "selling stock");
        stock.sellStock(quantity,trader);
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

    private class QueueManager extends Thread{
        @Override
        public void run() {
            while (true) {
                Trader trader;
                System.out.println("Wait" );
               synchronized (traderQueue){
                while (traderQueue.isEmpty()) {
                    try {
                        traderQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                }
                trader =  traderQueue.removeFirst();
                System.out.println("Get trader" );
                synchronized (trader) {
                    trader.notifyAll();
                    System.out.println("Notify trader");
                }
            }
        }
    }
}


