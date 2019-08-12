package com.example.valuta.demo.controller;

import com.example.valuta.demo.model.Currency;
import com.example.valuta.demo.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

//    @GetMapping("/currency")
//    public Currency getCurrency(){
//
//        return currencyService.getCurrency();
//    }
    @GetMapping("/currency")
    public Currency getCurrency(@RequestParam(required = false) String [] name){

        return currencyService.getCurrency(name);
    }

}
