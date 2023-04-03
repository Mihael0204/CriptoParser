package com.example.criptoparser.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ReportRow {
    private String currencyName;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
}
