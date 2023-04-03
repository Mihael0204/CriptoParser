package com.example.criptoparser.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.example.criptoparser.dto.CurrencyDto;
import com.example.criptoparser.dto.mapper.CurrencyPriceMapper;
import com.example.criptoparser.service.CurrencyPriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cryptocurrencies")
public class CurrencyPriceController {

    private final CurrencyPriceMapper currencyPriceMapper;
    private final CurrencyPriceService currencyPriceService;

    public CurrencyPriceController(CurrencyPriceMapper currencyPriceMapper,
                                   CurrencyPriceService currencyPriceService) {
        this.currencyPriceMapper = currencyPriceMapper;
        this.currencyPriceService = currencyPriceService;
    }

    @GetMapping("/minprice")
    public CurrencyDto getMinPrice(@Valid @RequestParam("name") String currencyName) {
        return currencyPriceMapper.toDto(currencyPriceService.getMinPrice(currencyName));
    }

    @GetMapping("/maxprice")
    public CurrencyDto getMaxPrice(@Valid @RequestParam("name") String currencyName) {
        return currencyPriceMapper.toDto(currencyPriceService.getMaxPrice(currencyName));
    }

    @GetMapping
    public List<CurrencyDto> getPriceByPage(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam("name") String currencyName
    ) {
        return currencyPriceService.getAll(currencyName, page, size)
                .stream()
                .map(currencyPriceMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/csv")
    public String createCsvReport() {
        return currencyPriceService.createCsvReport();
    }
}
