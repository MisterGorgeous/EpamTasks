package com.slabadniak.task3.stock;

public enum Ticker {
    MSFT("Microsoft Corp.", 45.49f, 8390771),
    AAPL("Apple Inc.", 117.64f, 5702722),
    FB("Facebook, Inc.", 127.87f, 7324578);

    private String fullName;
    private float price;
    private int quantity;

    Ticker(String fullName, float price, int quantity) {
        this.fullName = fullName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getfullName() {
        return fullName;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
