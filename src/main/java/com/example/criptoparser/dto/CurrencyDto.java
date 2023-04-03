package com.example.criptoparser.dto;

import java.math.BigInteger;
import com.example.criptoparser.validation.CurrencyType;
import lombok.Data;

@Data
public class CurrencyDto {
    private BigInteger id;
    private double price;
    @CurrencyType
    private String cryptoCurrency;
    private String fiatCurrency;
}