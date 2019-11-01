package com.example.valuta.demo.entity.currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currency")
public class CurrencyEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date Date;

    @Column(name = "bankname")
    private String bankName;

    @Column(name = "from_")
    private String from;

    @Column(name = "to_")
    private String to;

    @Column(name = "ratebuy")
    private Double rate_buy;

    @Column(name = "ratesell")
    private Double rate_sell;

}
