package com.slabadniak.task3.brocker;

import com.slabadniak.task3.market.Market;
import com.slabadniak.task3.exeption.IncorrectDataExeption;
import com.slabadniak.task3.stock.ProxyStock;
import org.apache.logging.log4j.Level;


public class Invester extends Trader {

    public Invester(String name, float money) {
        super(name, money);
    }

    @Override
    void buyStocks() {
        if (getMoney() == 0) {
            LOGGER.log(Level.INFO, this.toString() + " is bankrupt.");
            return;
        }
        ProxyStock stock = Market.getInstance().getMaxPriceStock();
        int stockValue = calculateStockValue(stock.getPrice());
        setBoughtStock(stock.buyStock(stockValue));
        setMoney(0);
        LOGGER.log(Level.INFO, this.toString());
    }

    @Override
    void sellStocks() {
        if (getBoughtStock() == null) {
            LOGGER.log(Level.INFO, this.toString() + " has nothing to sell.");
            return;
        }
        try {
            ProxyStock stock = Market.getInstance().getCertainStock(getBoughtStock().getId());
            for (int i = 0; i < 1000; ++i) {
                if (stock.getPrice() > getBoughtStock().getPrice()) {
                    break;
                }
            }
            takeMoney(stock.getPrice());
            stock.sellStock(getBoughtStock().getQuantity());
        } catch (IncorrectDataExeption e) {
            takeMoney(0);
        }
        setBoughtStock(null);
        LOGGER.log(Level.INFO, this.toString());
    }
}
