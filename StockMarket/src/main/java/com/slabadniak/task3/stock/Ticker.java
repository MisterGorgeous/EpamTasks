package com.slabadniak.task3.stock;

public enum Ticker {
    MSFT("Microsoft Corp.", 45.49f, 8390771),
    AAPL("Apple Inc.", 117.64f, 5702722),
    FB("Facebook, Inc.", 127.87f, 7324578),
    TSLA("Tesla Motors Inc", 196.71f, 6124903),
    NIKE("NIKE Inc.", 44.88f, 52013398),
    KO("Coca Cola Co.", 41.67f, 7821117),
    V("Visa Inc.", 82.58f, 6490654),
    DIS("Walt Disney Co.", 91.41f, 3290234),
    INTC("Intel Corp.", 37.45f, 6012078);

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
