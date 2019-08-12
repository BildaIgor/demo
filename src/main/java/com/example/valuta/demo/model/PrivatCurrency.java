package com.example.valuta.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PrivatCurrency extends AbstractCurrency {

     @JsonProperty("ccy")
     private String currencyFrom;

     @JsonProperty("base_ccy")
     private String currencyTo;

     @JsonProperty("buy")
     private String rateBuy;

     @JsonProperty("sale")
     private String rateSell;

}
