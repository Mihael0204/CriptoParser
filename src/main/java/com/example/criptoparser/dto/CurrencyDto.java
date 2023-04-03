package com.example.criptoparser.dto;

import com.example.criptoparser.validation.CurrencyType;
import java.math.BigInteger;
import lombok.Data;

@Data
public class CurrencyDto {
    private BigInteger id;
    private double price;
    @CurrencyType
    private String cryptoCurrency;
    private String fiatCurrency;
}
