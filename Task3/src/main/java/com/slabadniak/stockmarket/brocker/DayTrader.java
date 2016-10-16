package com.slabadniak.stockmarket.brocker;

import com.slabadniak.stockmarket.epamstockmarket.Market;
import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;
import com.slabadniak.stockmarket.stock.Stock;

public class DayTrader extends Trader implements Runnable {
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
            System.out.println(this);
            return;
        }
        Stock stock = Market.getInstance().getMinPriceStock();
        try {
            int stockValue = calculateStockValue(stock.getPrice());
            setBoughtStock(stock.buyStock(stockValue));
            setMoney(0);
        } catch (IncorrectDataExeption incorrectDataExeption) {
            incorrectDataExeption.printStackTrace();
        }
        System.out.println(this);
    }

    public void sellStocks() {
        try {
            Stock stock = Market.getInstance().getCertainStock(getBoughtStock().getId());
            float currentPrice = stock.getPrice();
            takeMoney(currentPrice);
            stock.sellStock(getBoughtStock().getQuantity());
            setBoughtStock(null);
        } catch (IncorrectDataExeption e) {
            takeMoney(0);
            setBoughtStock(null);
        }
        System.out.println(this);
    }
}
