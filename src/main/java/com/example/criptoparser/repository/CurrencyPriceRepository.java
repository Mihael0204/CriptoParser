package com.example.criptoparser.repository;

import com.example.criptoparser.model.CurrencyPrice;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyPriceRepository extends MongoRepository<CurrencyPrice, Long> {
    List<CurrencyPrice> findByCryptoCurrencyOrderByPriceAsc(String currency1);

    Page<CurrencyPrice> findByCryptoCurrencyOrderByPriceAsc(String currency1, Pageable pageable);

    CurrencyPrice findFirstByCryptoCurrencyOrderByPriceAsc(String currencyName);

    CurrencyPrice findFirstByCryptoCurrencyOrderByPriceDesc(String currencyName);
}
