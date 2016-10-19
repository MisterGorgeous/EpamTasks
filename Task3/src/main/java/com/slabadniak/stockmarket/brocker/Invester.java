package com.slabadniak.stockmarket.brocker;

import com.slabadniak.stockmarket.epamstockmarket.Market;

public class Invester extends Trader implements Runnable{
    public Invester(int id, float money, Market market) {
        super(id, money, market);
    }
    @Override
    void buyStocks() {

    }

    @Override
    void sellStocks() {

    }

    @Override
    public void run() {

    }
}
