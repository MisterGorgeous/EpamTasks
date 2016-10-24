package com.slabadniak.task3.brocker;

import com.slabadniak.task3.stock.Stock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Trader implements Runnable {
    public static final Logger LOGGER = LogManager.getLogger(Trader.class);
    private String name;
    private float money;
    private float startCapital;
    private Stock boughtStock;
    private boolean state;

    public Trader(String name, float money) {
        this.name = name;
        this.money = money;
        this.startCapital = money;
        this.state = true;
    }

    @Override
    public void run() {
        while (true) {
            if (isState()) {
                buyStocks();
            } else {
                sellStocks();
            }
            changeState();
        }
    }

    public float getMoney() {
        return money;
    }

    protected Stock getBoughtStock() {
        return boughtStock;
    }

    protected void setMoney(float money) {
        this.money = money;
    }

    protected void setBoughtStock(Stock boughtStock) {
        this.boughtStock = boughtStock;
    }

    protected int calculateStockValue(float stockPrice) {
        return (int) (money / stockPrice);
    }

    protected void takeMoney(float curentPrice) {
        money += boughtStock.getQuantity() * curentPrice;
    }

    protected void changeState() {
        state = !state;
    }

    protected boolean isState() {
        return state;
    }

    abstract void buyStocks();

    abstract void sellStocks();

    @Override
    public String toString() {
        return "Trader " + name + ", money=" + Math.round(money * 100) / 100 + ", startCapital=" + startCapital + ", Stocks=" +
                boughtStock + ' ';
    }
}
