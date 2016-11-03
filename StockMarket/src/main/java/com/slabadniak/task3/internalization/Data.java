package com.slabadniak.task3.internalization;

import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class Data {
    public static String RUSSIA = "RU";
    private static ResourceBundle rb = null;
    private final static float exchangeRate = 62.23f;


    public Data(ResourceBundle rb) {
        this.rb = rb;
    }

    public static String get(String data) {
        return rb.getString(data);
    }

    public static String convert(float value) {
        Currency currency;
        if (rb.getLocale().getCountry() == RUSSIA) {
            value *= exchangeRate;
            currency = Currency.getInstance(rb.getLocale());
        } else {
            currency = Currency.getInstance(Locale.US);
        }
        return Float.toString(value) + " " + currency.getSymbol();
    }

}
