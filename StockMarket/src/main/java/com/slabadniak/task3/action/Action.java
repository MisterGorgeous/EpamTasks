package com.slabadniak.task3.action;

import com.slabadniak.task3.brocker.DayTrader;
import com.slabadniak.task3.brocker.Invester;
import com.slabadniak.task3.market.Market;
import com.slabadniak.task3.internalization.Data;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Action {
    public static void main(String... args) {
        System.out.println(" 0 — русский \n любой — английский ");
        char i = 0;
        try {
            i = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String country = "";
        String language = "";
        if(i == '0'){
                country = Data.RUSSIA;
                language = Data.RUSSIA;
        }
        Locale current = new Locale(language, country);
        ResourceBundle rb = ResourceBundle.getBundle("def", current);
        Data data = new Data(rb);

        Market market = Market.getInstance();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(market);
        executor.submit(new DayTrader(Data.get("trader.JL"), 13000));
        executor.submit(new DayTrader(Data.get("trader.WDG"), 750));
        executor.submit(new Invester(Data.get("trader.GS"), 15000));
        executor.shutdown();
    }
}
