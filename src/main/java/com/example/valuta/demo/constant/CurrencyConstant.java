package com.example.valuta.demo.constant;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CurrencyConstant {
    public static final String PRIVAT_URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";
    public static final String MONO_URL = "https://api.monobank.ua/bank/currency";
    public static final List<String> PRIVAT_FOREIGN_CONCURRENCY = Arrays.asList("EUR","USD");
    public static final String PRIVAT_UAH = "UAH";
    public static final String PRIVAT_USD = "USD";
    public static final String PRIVAT_EUR = "EUR";
    public static final Integer MONO_UAH = 980;
    public static final Integer MONO_USD = 840;
    public static final Integer MONO_EUR = 978;
    public static final List<Integer> MONO_FOREIGN_CONCURRENCY = new ArrayList<Integer>() {
        {
            add(MONO_EUR);
            add(MONO_USD);
        }
    };
}
