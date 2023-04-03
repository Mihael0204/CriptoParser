package com.example.criptoparser.dto;

import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiCurrencyDto {
    private BigInteger id;
    @JsonProperty(value = "lprice")
    private double price;
    @JsonProperty(value = "curr1")
    private String cryptoCurrency;
    @JsonProperty(value = "curr2")
    private String fiatCurrency;
}
