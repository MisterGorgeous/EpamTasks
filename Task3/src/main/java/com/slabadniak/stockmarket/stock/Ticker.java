package com.slabadniak.stockmarket.stock;

public enum Ticker {
    MSFT("Microsoft Corp."), // 57.49
    AAPL("Apple Inc."), //117.64
    FB("Facebook, Inc."),             //127.87
    TSLA("Tesla Motors Inc"),   //196.71
    NIKE("NIKE Inc.") ,          //51.88
    KO("Coca Cola Co."),            //41.67
    V("Visa Inc."),                 //82.58
    DIS("Walt Disney Co."),         //91.41
    INTC("Intel Corp.");         //37.45

    private String fullName;

    Ticker(String fullName){
        this.fullName = fullName;
    }

    public String getfullName(){
        return fullName;
    }

}
