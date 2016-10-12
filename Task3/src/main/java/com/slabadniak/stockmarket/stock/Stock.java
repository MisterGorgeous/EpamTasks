package com.slabadniak.stockmarket.stock;

import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;

import java.util.concurrent.locks.Lock;

// end_sum / start_sum - 1

public class Stock {
    private long id;
    private float price;
    private int quantity;
    private Lock lock;
    //   private int quilibriumConstant;

    public Stock(long id, float price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public Stock buyStock(int quantity) throws IncorrectDataExeption {
        Stock stock;
        try {
            lock.lock();
            if (quantity > this.quantity) {
                quantity = this.quantity / 2;
            }
            stock = new Stock(id, price, quantity);
            price = (float) (price * Math.pow(((float) this.quantity / ((float) this.quantity - (float) quantity)), 2));
            this.quantity -= quantity;
        } finally {
            lock.unlock();
        }
        return stock;
    }

    public void sellStock(int quantity) {
        try {
            lock.lock();
            price = (float) (price * Math.pow(((float) this.quantity / ((float) this.quantity + (float) quantity)), 2));
            this.quantity += quantity;
        } finally {
            lock.unlock();
        }
    }

    public float getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
