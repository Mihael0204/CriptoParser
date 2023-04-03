package com.example.criptoparser.model;

import java.math.BigInteger;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class CurrencyPrice {
    @Id
    private BigInteger id;
    private double price;
    private String cryptoCurrency;
    private String fiatCurrency;
}
