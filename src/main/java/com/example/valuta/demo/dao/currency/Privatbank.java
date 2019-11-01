package com.example.valuta.demo.dao.currency;

import com.example.valuta.demo.dao.currency.BankModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Privatbank implements BankModel {

     @JsonProperty("ccy")
     private String from;

     @JsonProperty("base_ccy")
     private String to;

     @JsonProperty("buy")
     private Double buy;

     @JsonProperty("sale")
     private Double sell;
}
