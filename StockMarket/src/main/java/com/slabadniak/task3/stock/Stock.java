package com.slabadniak.task3.stock;

import com.slabadniak.task3.internalization.Data;

public class Stock implements IStock {
    private long id;
    private String ticker;
    private float price;
    private int quantity;

    public Stock(long id, String ticker, float price, int quantity) {
        this.id = id;
        this.ticker = ticker;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public Stock buyStock(int quantity) {
        Stock stock = new Stock(id, ticker, price, quantity);
        price = (float) (price * Math.pow(((float) this.quantity / ((float) this.quantity - (float) quantity)), 2));
        this.quantity -= quantity;
        return stock;
    }

    @Override
    public void sellStock(int quantity) {
        price = (float) (price * Math.pow(((float) this.quantity / ((float) this.quantity + (float) quantity)), 2));
        this.quantity += quantity;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
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
    public String getTicker() {
        return ticker;
    }

    @Override
    public String toString() {
        return Data.get("ticker." + ticker.substring(0,1))+ ", " + Data.get("P") +  Data.convert(Math.round(price * 100) / 100) +", " +  Data.get("Q") + quantity + ' ';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Stock other = (Stock) obj;
        return (other.getId() == this.getId()) && (other.getTicker().equals(this.getTicker()));
    }

    @Override
    public int hashCode() {
        int prime = 11;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((ticker == null) ? 0 : ticker.hashCode());
        return result;
    }
}
