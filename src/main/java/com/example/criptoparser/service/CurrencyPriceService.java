package com.example.criptoparser.service;

import java.util.List;
import com.example.criptoparser.model.CurrencyPrice;

public interface CurrencyPriceService {

    CurrencyPrice getMaxPrice(String currencyName);

    CurrencyPrice getMinPrice(String currencyName);

    List<CurrencyPrice> getAll(String currencyName, int page, int size);

    String createCsvReport();
}
