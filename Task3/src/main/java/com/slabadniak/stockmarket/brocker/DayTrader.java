package com.slabadniak.stockmarket.brocker;

import com.slabadniak.stockmarket.epamstockmarket.Market;
import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;
import com.slabadniak.stockmarket.stock.ProxyStock;
import com.slabadniak.stockmarket.stock.Stock;
import org.apache.logging.log4j.Level;

public class DayTrader extends Trader {
    public DayTrader(int id, float money, Market market) {
        super(id, money, market);
    }

    public void run() {
       for (int i = 0; i < 10; ++i) {
            if (isState()) {
              buyStocks();
            } else {
               sellStocks();
            }
            changeState();
        }
    }

    public void buyStocks() {
        if(getMoney() == 0){
           // LOGGER.log(Level.INFO, this.toString());
            return;
        }
        ProxyStock stock = Market.getInstance().getMinPriceStock();
        try {
            int stockValue = calculateStockValue(stock.getPrice());
            setBoughtStock(stock.buyStock(stockValue,this));
            setMoney(0);
        } catch (IncorrectDataExeption incorrectDataExeption) {
            incorrectDataExeption.printStackTrace();
        }
       // LOGGER.log(Level.INFO, this.toString());
        System.out.println( this.toString());
    }

    public void sellStocks() {
        float currentPrice = 0;
        try {
            ProxyStock stock = Market.getInstance().getCertainStock(getBoughtStock().getId());
            currentPrice = stock.getPrice();
            takeMoney(currentPrice);
            stock.sellStock(getBoughtStock().getQuantity(),this);
            setBoughtStock(null);
        } catch (IncorrectDataExeption e) {
            takeMoney(0);
            setBoughtStock(null);
        }
        //LOGGER.log(Level.INFO, this.toString());
        System.out.println( this.toString() + " " +  currentPrice);
    }
}
