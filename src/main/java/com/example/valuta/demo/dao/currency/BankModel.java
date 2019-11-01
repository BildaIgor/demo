package com.example.valuta.demo.dao.currency;

public interface BankModel<MODEL> {
    String getFrom();
    String getTo();
    Double getBuy();
    Double getSell();;
}
