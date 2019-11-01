package com.example.valuta.demo.service.impl;

import com.example.valuta.demo.dao.currency.Monobank;
import com.example.valuta.demo.service.AbstractCurrencyService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MonobankCurrencyService extends AbstractCurrencyService<Monobank> {
    public MonobankCurrencyService(RestTemplate restTemplate) {
        super(restTemplate, Monobank[].class);
    }

    @Override
    public String getApiUrl() {
        return "https://api.monobank.ua/bank/currency";
    }

    @Override
    public String getBankName() {
        return "Monobank";
    }
}
