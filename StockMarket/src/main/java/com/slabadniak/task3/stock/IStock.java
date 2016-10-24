package com.slabadniak.task3.stock;


public interface IStock {
    Stock buyStock(int quantity);

    void sellStock(int quantity);

    float getPrice();

    long getId();

    int getQuantity();

    void setPrice(float price);

    String getTicker();
}
