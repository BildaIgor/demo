package com.example.valuta.demo.repository.currency;

import com.example.valuta.demo.entity.currency.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyEntityRepository extends JpaRepository<CurrencyEntity, Long> {

}
