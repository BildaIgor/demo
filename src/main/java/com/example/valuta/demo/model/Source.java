package com.example.valuta.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Source {
    private String name1;
    private String name2;
    private Float buyValue;
    private Float saleValue;
    private Float avgValue;
}
