package com.slabadniak.stockmarket.brocker;

import com.slabadniak.stockmarket.constant.Constant;
import com.slabadniak.stockmarket.epamstockmarket.Market;
import com.slabadniak.stockmarket.stock.Stock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;

public abstract class Trader {
    public static final Logger LOGGER = LogManager.getLogger(Trader.class);
    private int id;
    private float money;
    private float startCapital;
    private Stock boughtStock;
    private boolean state;

    static {
        LoggerContext context = (LoggerContext) LogManager.getContext(true);
        context.setConfigLocation(new File(Constant.LOG_PATH).toURI());
    }

    public Trader(int id, float money, Market market) {
        this.id = id;
        this.money = money;
        this.startCapital = money;
        //this.market = market;
        this.state = true;
    }

    abstract void buyStocks();

    abstract void sellStocks();

    protected void setBoughtStock(Stock boughtStock) {
        this.boughtStock = boughtStock;
    }

    protected Stock getBoughtStock() {
        return boughtStock;
    }

    protected void setMoney(float money) {
        this.money = money;
    }

    protected int calculateStockValue(float stockPrice) {
        return (int) (money / stockPrice);
    }

    public float getMoney() {
        return money;
    }

    protected void takeMoney(float curentPrice) {
        money += boughtStock.getQuantity() * curentPrice;
        money = Math.round(money * 100) / 100;
    }

    protected void changeState(){
        state = !state;
    }

    protected boolean isState() {
        return state;
    }

    @Override
    public String toString() {
        return "Trader " +
                "id=" + id +
                ", money=" + money +
                ", startCapital=" + startCapital +
                ", boughtStock=" + boughtStock +
                ", state=" + state +
                ' ';
    }
}
