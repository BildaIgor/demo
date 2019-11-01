package com.example.valuta.demo.service.impl;

import com.example.valuta.demo.dao.currency.Privatbank;
import com.example.valuta.demo.service.AbstractCurrencyService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PrivatCurrencyService extends AbstractCurrencyService<Privatbank> {
    public PrivatCurrencyService(RestTemplate restTemplate) {
        super(restTemplate, Privatbank[].class);
    }

    @Override
    public String getApiUrl() {
        return "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";
    }

    @Override
    public String getBankName() {
        return "Privat";
    }
}
