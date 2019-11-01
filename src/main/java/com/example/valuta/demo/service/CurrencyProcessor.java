package com.example.valuta.demo.service;

import com.example.valuta.demo.converter.CurrencyConverter;
import com.example.valuta.demo.dao.currency.Currency;
import com.example.valuta.demo.repository.currency.CurrencyEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class CurrencyProcessor {
    private final GeneralCurrencyService generalCurrencyService;
    private final CurrencyEntityRepository currencyEntityRepository;

    @Scheduled(fixedRate = 10000)
    public void saveCurrencyToDb(){
        Currency currency = generalCurrencyService.getCurrency(new String[]{"def"}, new String[]{"def"});
        currencyEntityRepository.saveAll(CurrencyConverter.toEntity(currency));
    }
}
