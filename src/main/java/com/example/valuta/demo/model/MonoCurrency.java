package com.example.valuta.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MonoCurrency extends AbstractCurrency {

    @JsonProperty("currencyCodeA")
    private Integer currencyFrom;

    @JsonProperty("currencyCodeB")
    private Integer currencyTo;

    @JsonProperty("rateBuy")
    private Float rateBuy;

    @JsonProperty("rateSell")
    private Float rateSell;


}
