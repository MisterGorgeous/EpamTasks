package com.slabadniak.task3.stock;

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
    public String toString() {
        return ticker + ", price=" + Math.round(price * 100) / 100 + ", quantity=" + quantity + ' ';
    }

}
