package com.example.valuta.demo.service;

import com.example.valuta.demo.dao.currency.BankModel;
import com.example.valuta.demo.dao.currency.Currency;
import com.example.valuta.demo.repository.currency.CurrencyEntityRepository;
import com.example.valuta.demo.service.impl.MonobankCurrencyService;
import com.example.valuta.demo.service.impl.PrivatCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeneralCurrencyService {

    private final MonobankCurrencyService monobankCurrencyService;
    private final PrivatCurrencyService privatCurrencyService;
    @Autowired
    private CurrencyEntityRepository repository;
    public Currency getCurrency(String[] banksNames, String[] argCurrencies) {

        Currency currency = new Currency();
        currency.setDate(new Date());
        Map<String, List<BankModel>> sources = new HashMap<>();

        if ("def".equals(banksNames[0])) {
            sources.put(monobankCurrencyService.getBankName(), monobankCurrencyService.getCurrentCurrencies(argCurrencies));
            sources.put(privatCurrencyService.getBankName(), privatCurrencyService.getCurrentCurrencies(argCurrencies));
        } else {
            for (String bankName : banksNames
            ) {
                switch (bankName) {
                    case "Privat": {
                        sources.put(privatCurrencyService.getBankName(), privatCurrencyService.getCurrentCurrencies(argCurrencies));
                        break;
                    }
                    case "Mono": {
                        sources.put(monobankCurrencyService.getBankName(), monobankCurrencyService.getCurrentCurrencies(argCurrencies));
                        break;
                    }
                }
            }
        }

        currency.setSource(sources);
        return currency;

    }
}