package com.example.valuta.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractCurrency {
    private Integer currencyFrom;
    private Integer currencyTo;
    private Float rateBuy;
    private Float rateSell;

}
