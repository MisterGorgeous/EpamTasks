package com.slabadniak.stockmarket.epamstockmarket;

import com.slabadniak.stockmarket.brocker.Brocker;
import com.slabadniak.stockmarket.stock.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class EpamStockMarket {
    private CopyOnWriteArrayList<Stock> stocks;

    public EpamStockMarket() {
        stocks = new CopyOnWriteArrayList<Stock>() {
            {
                add( new Stock(1, 2.45f, 3000));
                add(new Stock(2, 0.60f, 13000));
                add( new Stock(3, 1.48f, 8300));
                add(new Stock(4, 0.70f, 10000));
            }
        };
    }

    public CopyOnWriteArrayList<Stock> getStocks() {
        return stocks;
    }
}
