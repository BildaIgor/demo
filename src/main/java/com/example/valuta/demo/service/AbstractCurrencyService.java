package com.example.valuta.demo.service;

import com.example.valuta.demo.constant.CurrencyConstant;
import com.example.valuta.demo.dao.currency.BankModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractCurrencyService<MODEL extends BankModel> {
    private final RestTemplate restTemplate;
    private final Class<MODEL[]> clazz;

    public List<BankModel> getCurrentCurrencies(String[] argCurrencies) {
        ResponseEntity<MODEL[]> response = restTemplate.getForEntity(getApiUrl(), clazz);
        List<MODEL> filteredCurrencies = new ArrayList<>();
        try {
            if (HttpStatus.OK.equals(response.getStatusCode())) {
                MODEL[] currencies = response.getBody();
                if (currencies != null) {
                    for (MODEL currency : currencies) {
                        if ((argCurrencies != null) && (!"def".equals(argCurrencies[0]))) {
                            List<String> listArgCurrencies = new ArrayList<>(Arrays.asList(argCurrencies));
                            if (listArgCurrencies.contains(currency.getFrom())
                                    && (CurrencyConstant.UAH_CURRENCY.equals(currency.getTo()))) {
                                filteredCurrencies.add(currency);
                            }
                        } else if (CurrencyConstant.FOREIGN_CONCURRENCY.contains(currency.getFrom())
                                && CurrencyConstant.UAH_CURRENCY.equals(currency.getTo())) {
                            filteredCurrencies.add(currency);
                        }
                    }
                }
            }
        } catch (HttpClientErrorException e){
            getCurrentCurrencies(argCurrencies);
        }


        return (List<BankModel>) filteredCurrencies;
    }

    public abstract String getApiUrl();

    public abstract String getBankName();
}
