package com.slabadniak.stockmarket.brocker;

import com.slabadniak.stockmarket.epamstockmarket.Market;
import com.slabadniak.stockmarket.stock.Stock;

public abstract class Trader implements TraderBehavior {
    private int id;
    private float money;
    private float startCapital;
    private Stock boughtStock;
    private boolean state;

    public Trader(int id, float money, Market market) {
        this.id = id;
        this.money = money;
        this.startCapital = money;
        //this.market = market;
        this.state = true;
    }

    protected void setBoughtStock(Stock boughtStock) {
        this.boughtStock = boughtStock;
    }

    protected Stock getBoughtStock() {

        return boughtStock;
    }

    protected void setMoney(float money) {
        this.money = money;
    }

    protected int calculateStockValue(float stockPrice) {
        return (int) (money / stockPrice);
    }

    public float getMoney() {
        return money;
    }

    protected void takeMoney(float curentPrice) {
        money += boughtStock.getQuantity() * curentPrice;
    }

    protected void changeState(){
        state = !state;
    }

    protected boolean isState() {
        return state;
    }


    @Override
    public String toString() {
        return "Trader{" +
                "id=" + id +
                ", money=" + money +
                ", startCapital=" + startCapital +
                ", boughtStock=" + boughtStock +
                ", state=" + state +
                '}';
    }
}
