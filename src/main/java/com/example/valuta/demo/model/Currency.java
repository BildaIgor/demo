package com.example.valuta.demo.model;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {

    private Date date;
    private Map<String,List<Source>> sources;



}
