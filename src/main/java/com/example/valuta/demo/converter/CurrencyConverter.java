package com.example.valuta.demo.converter;

import com.example.valuta.demo.entity.currency.CurrencyEntity;
import com.example.valuta.demo.dao.currency.BankModel;
import com.example.valuta.demo.dao.currency.Currency;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
public  class CurrencyConverter {

    public static List<CurrencyEntity> toEntity(Currency currency){
        Date date = currency.getDate();
        List<CurrencyEntity> currencyEntities = new ArrayList<>();

        for (Map.Entry entry: currency.getSource().entrySet()
        ) {
            for (BankModel bankModel: (List<BankModel>) entry.getValue()
            ) {
                currencyEntities.add(CurrencyEntity.builder()
                        .Date(date)
                        .bankName((String)entry.getKey())
                        .from(bankModel.getFrom())
                        .to(bankModel.getTo())
                        .rate_buy(bankModel.getBuy())
                        .rate_sell(bankModel.getSell())
                        .build());
            }

        }
        return currencyEntities;
    }
}
