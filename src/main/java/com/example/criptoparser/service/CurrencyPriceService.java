package com.example.criptoparser.service;

import com.example.criptoparser.model.CurrencyPrice;
import java.util.List;

public interface CurrencyPriceService {

    CurrencyPrice getMaxPrice(String currencyName);

    CurrencyPrice getMinPrice(String currencyName);

    List<CurrencyPrice> getAll(String currencyName, int page, int size);

    String createCsvReport();
}
