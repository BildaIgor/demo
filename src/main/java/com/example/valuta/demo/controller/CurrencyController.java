package com.example.valuta.demo.controller;

import com.example.valuta.demo.dao.currency.BankModel;
import com.example.valuta.demo.dao.currency.Currency;
import com.example.valuta.demo.service.CurrencyProcessor;
import com.example.valuta.demo.service.GeneralCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.*;

//@RestController
@RequiredArgsConstructor
@Controller
public class CurrencyController {

    private final GeneralCurrencyService generalCurrencyService;

//    @GetMapping("/currency")
//    @ResponseBody
//    public Currency getCurrency(@RequestParam(name = "bankName",defaultValue = "def") String[] banksNames,
//                                @RequestParam(name = "argCurrency",defaultValue = "def") String[] argCurrencies
//    ){
//        return generalCurrencyService.getCurrency(banksNames,argCurrencies);
//    }

    @GetMapping("/main")
    public String getCurrency(Map<String,Object> model
    ){

        return "main";
    }
    @PostMapping("/main")
    public String main(@RequestParam(name = "banks",defaultValue = "def") String banksNames,
                       @RequestParam(name = "currencies",defaultValue = "def") String argCurrencies,
                       Map<String,Object> model){
        String [] bankNames = banksNames.split(",");
        String [] currencies = argCurrencies.split(",");
        Currency currency = generalCurrencyService.getCurrency(bankNames,currencies);
        model.put("date",currency.getDate());
        Set<Map.Entry<String,List<BankModel>>> entrySet = currency.getSource().entrySet();
        model.put("entrySet",entrySet);
        return "viewWithFilter";
    }

    @GetMapping("/")
    public String getHome(Map<String,Object> model){
        Currency currency = generalCurrencyService.getCurrency(new String[]{"def"},new String[]{"def"});
        model.put("date",currency.getDate());
        Set<Map.Entry<String,List<BankModel>>> entrySet = currency.getSource().entrySet();
        model.put("entrySet",entrySet);
        return "home";
    }




}
