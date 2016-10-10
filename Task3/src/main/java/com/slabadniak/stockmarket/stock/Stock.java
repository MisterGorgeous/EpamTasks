package com.slabadniak.stockmarket.stock;

import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;

public class Stock {
    private long id;
    private float price;
    private int quantity;
 //   private int quilibriumConstant;

    public Stock(long id, float price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public Stock getQuantity(int quantity) throws IncorrectDataExeption{
        if(quantity < 0 || quantity > this.quantity){
            throw new IncorrectDataExeption("To do");
        }
        price = (float) (price *   Math.pow((this.quantity / (this.quantity - quantity)) ,2));
        return new Stock(id, price,quantity);
    }

    public void setQuantity(int quantity) {
        price = (float) (price *   Math.pow((this.quantity / (this.quantity + quantity)) ,2));
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    public int showQ(){
        return quantity;
    }

}
