package com.slabadniak.stockmarket.stock;

import com.slabadniak.stockmarket.brocker.Trader;
import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// end_sum / start_sum - 1

public class Stock implements IStock {
    private long id;
    private float price;
    private int quantity;
    //   private int quilibriumConstant;

    public Stock(long id, float price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public Stock buyStock(int quantity) {
        Stock stock;
        if (quantity > this.quantity) {
            quantity = this.quantity / 2;
        }
        stock = new Stock(id, price, quantity);
        price = (float) (price * Math.pow(((float) this.quantity / ((float) this.quantity - (float) quantity)), 2));
        price = Math.round(price * 100) / 100;
        if (quantity < this.quantity / 2) {
            this.quantity -= quantity;
        }
        return stock;
    }

    @Override
    public void sellStock(int quantity) {
        price = (float) (price * Math.pow(((float) this.quantity / ((float) this.quantity + (float) quantity)), 2));
        price = Math.round(price * 100) / 100;
        this.quantity += quantity;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock " +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ' ';
    }
}
