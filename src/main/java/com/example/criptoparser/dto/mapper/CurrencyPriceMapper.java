package com.example.criptoparser.dto.mapper;

import com.example.criptoparser.dto.ApiCurrencyDto;
import com.example.criptoparser.dto.CurrencyDto;
import com.example.criptoparser.model.CurrencyPrice;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CurrencyPriceMapper {
    public CurrencyPrice toModel(ApiCurrencyDto apiCurrencyDto) {
        CurrencyPrice currencyPrice = new CurrencyPrice();
        currencyPrice.setPrice(BigDecimal.valueOf(apiCurrencyDto.getPrice()));
        currencyPrice.setCryptoCurrency(apiCurrencyDto.getCryptoCurrency());
        currencyPrice.setFiatCurrency(apiCurrencyDto.getFiatCurrency());
        return currencyPrice;
    }

    public CurrencyPrice toModel(CurrencyDto currencyDto) {
        CurrencyPrice currencyPrice = new CurrencyPrice();
        currencyPrice.setPrice(BigDecimal.valueOf(currencyDto.getPrice()));
        currencyPrice.setCryptoCurrency(currencyDto.getCryptoCurrency());
        currencyPrice.setFiatCurrency(currencyDto.getFiatCurrency());
        return currencyPrice;
    }

    public CurrencyDto toDto(CurrencyPrice currencyPrice) {
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setId(currencyPrice.getId());
        currencyDto.setPrice(currencyPrice.getPrice().doubleValue());
        currencyDto.setCryptoCurrency(currencyPrice.getCryptoCurrency());
        currencyDto.setFiatCurrency(currencyPrice.getFiatCurrency());
        return currencyDto;
    }
}
