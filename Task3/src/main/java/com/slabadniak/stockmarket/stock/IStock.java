package com.slabadniak.stockmarket.stock;

import com.slabadniak.stockmarket.brocker.Trader;
import com.slabadniak.stockmarket.constant.Constant;
import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;

public interface IStock {
    public static final Logger LOGGER = LogManager.getLogger(IStock.class);

    Stock buyStock(int quantity, Trader trader) throws IncorrectDataExeption;

    void sellStock(int quantity, Trader trader);

    float getPrice();

    long getId();

    int getQuantity();

    void setPrice(float price);

    Stock copyStock();
}
