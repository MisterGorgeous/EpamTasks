package com.slabadniak.stockmarket.stock;

import com.slabadniak.stockmarket.brocker.Trader;
import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;

public interface IStock {
    Stock buyStock(int quantity, Trader trader) throws IncorrectDataExeption;

    void sellStock(int quantity, Trader trader);

    float getPrice();

    long getId();

    int getQuantity();

    void setPrice(float price);

    Stock copyStock();
}
