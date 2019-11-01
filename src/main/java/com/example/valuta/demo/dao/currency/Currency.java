package com.example.valuta.demo.dao.currency;

import com.example.valuta.demo.dao.currency.BankModel;
import lombok.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private Date date;
    private Map<String,List<BankModel>> source = new HashMap<>();



}
