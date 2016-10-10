package com.slabadniak.stockmarket.brocker;

import com.slabadniak.stockmarket.epamstockmarket.EpamStockMarket;
import com.slabadniak.stockmarket.exeption.IncorrectDataExeption;
import com.slabadniak.stockmarket.stock.Stock;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Thread.sleep;

public abstract class Brocker implements Runnable {
    private int id;
    private float money;
    private float startCopital = money;
    private EpamStockMarket market;
    private Stock buiedStock;

    public Brocker(int id, float money,EpamStockMarket market) {
        this.id = id;
        this.money = money;
        this.market = market;
    }

    public void run() {
        CopyOnWriteArrayList<Stock> stocks = market.getStocks();
        while(true){
            Stock lowestPriceStock = stocks.get(0);
            for(Stock stock:stocks)
                if(stock.getPrice() < lowestPriceStock.getPrice()){
                    lowestPriceStock = stock;
                }
            buiedStock = lowestPriceStock.getQuantity(calculateQuantity(lowestPriceStock.getPrice()));
            System.out.println("Brocker " + id +"startc " + startCopital + "bought " + buiedStock.getId()+" " +  buiedStock.showQ()+ " " + buiedStock.getPrice());
            income();
            buiedStock.setQuantity(buiedStock.showQ());
            buiedStock = null;
        }
    }

    private int calculateQuantity(float stockPrice) throws IncorrectDataExeption {
        if(stockPrice == 0){
            throw new IncorrectDataExeption("incor price");
        }
        return (int) (money/stockPrice);
    }

    private float income(){
        money += buiedStock.showQ() * buiedStock.getPrice();
        return money - startCopital;
    }


}
