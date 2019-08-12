package com.example.valuta.demo.service;

import com.example.valuta.demo.constant.CurrencyConstant;
import com.example.valuta.demo.model.*;
import com.example.valuta.demo.model.Currency;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.example.valuta.demo.constant.CurrencyConstant.MONO_UAH;

@Service
public class CurrencyService {
    private RestTemplate restTemplate = new RestTemplate();

//    public Currency getCurrency() {
//        Currency currency = new Currency();
//        currency.setDate(new Date());
//        Map<String,List<Source>> banks = new HashMap<>();
//        List<Source> privatSources = new ArrayList<>();
//        List<Source> monoSources = new ArrayList<>();
//        privatSources.add(getPrivatDataUSD());
//        privatSources.add(getPrivatDataEUR());
//        monoSources.add(getMonoDataUSD());
//        monoSources.add(getMonoDataEUR());
//
//        banks.put("Privat" , privatSources );
//        banks.put("Mono" , monoSources);
//
//        currency.setSources(banks);
//        return currency;
//    }
//    public Currency getCurrency(String [] name) {
//        Currency currency = new Currency();
//        currency.setDate(new Date());
//        Map<String, List<Source>> banks = new HashMap<>();
//        List<Source> privatSources = new ArrayList<>();
//        List<Source> monoSources = new ArrayList<>();
//        if(name!= null){
//            for(String str :name) {
//                switch (str) {
//                    case "Privat": {
//                        privatSources.add(getPrivatDataUSD());
//                        privatSources.add(getPrivatDataEUR());
//                        banks.put("Privat", privatSources);
//                        break;
//                    }
//                    case "Mono": {
//                        monoSources.add(getMonoDataUSD());
//                        monoSources.add(getMonoDataEUR());
//                        banks.put("Mono", monoSources);
//                        break;
//                    }
//                }
//            }
//            } else {
//                    privatSources.add(getPrivatDataUSD());
//                    privatSources.add(getPrivatDataEUR());
//                    banks.put("Privat", privatSources);
//                    monoSources.add(getMonoDataUSD());
//                    monoSources.add(getMonoDataEUR());
//                    banks.put("Mono", monoSources);
//
//                }
//
//        currency.setSources(banks);
//        return currency;
//    }

    public Currency getCurrency(String [] namesOfBank ) {
        List<Source> sources = new ArrayList<>();
        ResponseEntity response;
        Currency currency = new Currency();
        currency.setDate(new Date());
        Map<String, List<Source>> banks = new HashMap<>();
        for (String name : namesOfBank
        ) {
            switch (name) {
                case "Privat": {
                    response = restTemplate.getForEntity(CurrencyConstant.PRIVAT_URL, PrivatCurrency[].class);
                    if (HttpStatus.OK.equals(response.getStatusCode())) {
                        PrivatCurrency[] privatCurrencies = (PrivatCurrency[]) response.getBody();
                        if (privatCurrencies != null) {
                            for (PrivatCurrency privatCurrency : privatCurrencies) {
                                if (CurrencyConstant.PRIVAT_USD.equals(privatCurrency.getCurrencyFrom())
                                        && CurrencyConstant.PRIVAT_UAH.equals(privatCurrency.getCurrencyTo())) {
                                    sources.add(Source.builder()
                                            .name1(privatCurrency.getCurrencyFrom())
                                            .name2(privatCurrency.getCurrencyTo())
                                            .buyValue(Float.parseFloat(privatCurrency.getRateBuy()))
                                            .saleValue(Float.parseFloat(privatCurrency.getRateSell()))
                                            .avgValue(getAvgValueForPrivat(privatCurrency.getRateBuy(), privatCurrency.getRateSell()))
                                            .build());
                                }
                            }
                        }
                    }
                    break;
                }
                case "Mono": {
                    response = restTemplate.getForEntity(CurrencyConstant.MONO_URL, MonoCurrency[].class);
                    break;
                }

            }
            banks.put(name, sources);
        }

        currency.setSources(banks);
        return currency;
    }





    private Source getPrivatDataUSD() {
        ResponseEntity<PrivatCurrency[]> response = restTemplate.getForEntity(CurrencyConstant.PRIVAT_URL, PrivatCurrency[].class);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            PrivatCurrency[] privatCurrencies = response.getBody();
            if (privatCurrencies != null) {
                for (PrivatCurrency privatCurrency : privatCurrencies) {
                    if (CurrencyConstant.PRIVAT_USD.equals(privatCurrency.getCurrencyFrom())
                            && CurrencyConstant.PRIVAT_UAH.equals(privatCurrency.getCurrencyTo())) {
                        return Source.builder()
                                .name1(privatCurrency.getCurrencyFrom())
                                .name2(privatCurrency.getCurrencyTo())
                                .buyValue(Float.parseFloat(privatCurrency.getRateBuy()))
                                .saleValue(Float.parseFloat(privatCurrency.getRateSell()))
                                .avgValue(getAvgValueForPrivat(privatCurrency.getRateBuy(),privatCurrency.getRateSell()))
                                .build();
                    }
                }
            }
        }
        return null;
    }
    private Source getPrivatDataEUR() {
        ResponseEntity<PrivatCurrency[]> response = restTemplate.getForEntity(CurrencyConstant.PRIVAT_URL, PrivatCurrency[].class);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            PrivatCurrency[] privatCurrencies = response.getBody();
            if (privatCurrencies != null) {
                for (PrivatCurrency privatCurrency : privatCurrencies) {
                    if (CurrencyConstant.PRIVAT_EUR.equals(privatCurrency.getCurrencyFrom())
                            && CurrencyConstant.PRIVAT_UAH.equals(privatCurrency.getCurrencyTo())) {
                        return Source.builder()
                                .name1(privatCurrency.getCurrencyFrom())
                                .name2(privatCurrency.getCurrencyTo())
                                .buyValue(Float.parseFloat(privatCurrency.getRateBuy()))
                                .saleValue(Float.parseFloat(privatCurrency.getRateSell()))
                                .avgValue(getAvgValueForPrivat(privatCurrency.getRateBuy(),privatCurrency.getRateSell()))
                                .build();
                    }
                }
            }
        }
        return null;
    }

    public Source getMonoDataUSD() {
        ResponseEntity<MonoCurrency[]> response = restTemplate.getForEntity(CurrencyConstant.MONO_URL, MonoCurrency[].class);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            MonoCurrency[] monoCurrencies = response.getBody();
            if (monoCurrencies != null) {
                for (MonoCurrency monoCurrency : monoCurrencies) {
                    if (CurrencyConstant.MONO_USD.equals(monoCurrency.getCurrencyFrom())
                            && MONO_UAH.equals(monoCurrency.getCurrencyTo())) {
                        return Source.builder()
                                .name1(getCurrencyNameFromCode(monoCurrency.getCurrencyFrom()))
                                .name2(getCurrencyNameFromCode(monoCurrency.getCurrencyTo()))
                                .buyValue(monoCurrency.getRateBuy())
                                .saleValue(monoCurrency.getRateSell())
                                .avgValue(getAvgValueForMono(monoCurrency.getRateBuy(),monoCurrency.getRateSell()))
                                .build();
                    }
                }
            }
        }
        return null;
    }
    public Source getMonoDataEUR() {
        ResponseEntity<MonoCurrency[]> response = restTemplate.getForEntity(CurrencyConstant.MONO_URL, MonoCurrency[].class);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            MonoCurrency[] monoCurrencies = response.getBody();
            if (monoCurrencies != null) {
                for (MonoCurrency monoCurrency : monoCurrencies) {
                    if (CurrencyConstant.MONO_EUR.equals(monoCurrency.getCurrencyFrom())
                            && MONO_UAH.equals(monoCurrency.getCurrencyTo())) {
                        return Source.builder()
                                .name1(getCurrencyNameFromCode(monoCurrency.getCurrencyFrom()))
                                .name2(getCurrencyNameFromCode(monoCurrency.getCurrencyTo()))
                                .buyValue(monoCurrency.getRateBuy())
                                .saleValue(monoCurrency.getRateSell())
                                .avgValue(getAvgValueForMono(monoCurrency.getRateBuy(),monoCurrency.getRateSell()))
                                .build();
                    }
                }
            }
        }
        return null;
    }
    private Float getAvgValueForPrivat(String buy , String sale){
        Float result = (Float.parseFloat(buy) + Float.parseFloat(sale)) / 2 ;
        return result;
    }
    private Float getAvgValueForMono(Float buy, Float sale){
        Float result = (buy + sale) / 2 ;
        return result;
    }

    private String getCurrencyNameFromCode(Integer code) {
        switch (code) {
            case 980:
                return "UAH";
            case 840:
                return "USD";
            case 978:
                return "EUR";
            default:
                return null;
        }
    }
}
