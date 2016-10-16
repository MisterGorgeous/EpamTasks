package com.slabadniak.stockmarket.brocker;

import com.slabadniak.stockmarket.stock.Stock;
import com.slabadniak.stockmarket.stock.Ticker;

public interface TraderBehavior {
    void buyStocks();
    void sellStocks();
   /* default int calculateStockValue(float stockPrice) {
        return (int) (money / stockPrice);
    }

    default void takeMoney(float curentPrice) {
        money += boughtStock.getQuantity() * curentPrice;
    }*/
}
