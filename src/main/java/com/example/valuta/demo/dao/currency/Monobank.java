package com.example.valuta.demo.dao.currency;

import com.example.valuta.demo.dao.currency.BankModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.Optional;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Monobank implements BankModel {

    @JsonIgnore
    // @Column(name = "date")
    private Date date;

    //  @Column(name = "from")
    @JsonProperty("currencyCodeA")
    private String from;

    @JsonProperty("currencyCodeB")
    private String to;

    @JsonProperty("rateBuy")
    private Double buy;

    @JsonProperty("rateSell")
    private Double sell;

    @Override
    public String getFrom() {
        return Optional.of(from)//Optional=необязательный
                .map(this::getCurrencyNameFromCode)
                .orElse(null);
    }

    @Override
    public String getTo() {
        return Optional.of(to)
                .map(this::getCurrencyNameFromCode)
                .orElse(null);
    }

    private String getCurrencyNameFromCode(String code) {
        switch (code) {
            case "980":
                return "UAH";
            case "840":
                return "USD";
            case "978":
                return "EUR";
            default:
                return null;
        }
    }
}