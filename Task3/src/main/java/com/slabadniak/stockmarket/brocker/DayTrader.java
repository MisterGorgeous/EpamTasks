package com.slabadniak.stockmarket.brocker;

import com.slabadniak.stockmarket.epamstockmarket.Market;
import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;
import com.slabadniak.stockmarket.stock.Stock;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class DayTrader extends Brocker {
    public DayTrader(int id, float money, Market market) {
        super(id, money, market);
    }

    public void run() {
        ArrayList<Stock> stocks = getMarket().getStocks();
        for (int i = 0; i < 10; ++i) {
            if (isState()) {
                int lowestPriceStock = 0;
                for (Stock stock : stocks) {
                    if (stock.getPrice() < stocks.get(lowestPriceStock).getPrice()) {
                        lowestPriceStock = (int) stock.getId();
                    }
                }
                try {
                    int stockValue = calculateStockValue(stocks.get(lowestPriceStock).getPrice());
                    setBoughtStock(stocks.get(lowestPriceStock).buyStock(stockValue));
                    setMoney(0);
                } catch (IncorrectDataExeption incorrectDataExeption) {
                    incorrectDataExeption.printStackTrace();
                }
                changeState();
                System.out.println(this);
            } else {
                float currentPrice = stocks.get((int)getBoughtStock().getId()).getPrice();
                getMoney(currentPrice);
                stocks.get((int) getBoughtStock().getId()).sellStock(getBoughtStock().getQuantity());
                setBoughtStock(null);
                changeState();
                System.out.println(this);
            }
        }
    }
}
