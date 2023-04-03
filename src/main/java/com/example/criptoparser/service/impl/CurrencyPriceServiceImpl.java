package com.example.criptoparser.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.criptoparser.dto.ApiCurrencyDto;
import com.example.criptoparser.dto.mapper.CurrencyPriceMapper;
import com.example.criptoparser.model.CurrencyPrice;
import com.example.criptoparser.model.ReportRow;
import com.example.criptoparser.repository.CurrencyPriceRepository;
import com.example.criptoparser.service.CurrencyPriceService;
import com.example.criptoparser.service.FileWriter;
import com.example.criptoparser.service.HttpClient;
import com.example.criptoparser.service.ReportCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CurrencyPriceServiceImpl  implements CurrencyPriceService {
    private static final String WRITE_TO = "src/main/resources/result.csv";
    private static final Set<String> currencies = Set.of("BTC", "ETH", "XRP");
    private final HttpClient httpClient;
    private final CurrencyPriceRepository currencyPriceRepository;
    private final CurrencyPriceMapper currencyPriceMapper;
    private final FileWriter fileWriter;
    private final ReportCreator reportCreator;
    @Value("${api.url}")
    private String apiUrl;

    public CurrencyPriceServiceImpl(HttpClient httpClient,
                                    CurrencyPriceRepository currencyPriceRepository,
                                    CurrencyPriceMapper currencyPriceMapper,
                                    FileWriter fileWriter,
                                    ReportCreator reportCreator) {
        this.httpClient = httpClient;
        this.currencyPriceRepository = currencyPriceRepository;
        this.currencyPriceMapper = currencyPriceMapper;
        this.fileWriter = fileWriter;
        this.reportCreator = reportCreator;
    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void syncExternalCurrency() {
        for (String currency: currencies) {
                ApiCurrencyDto apiCurrencyDto =
                    httpClient.get(apiUrl + currency + "/USD", ApiCurrencyDto.class);
                currencyPriceRepository.save(currencyPriceMapper.toModel(apiCurrencyDto));
        }
    }

    @Override
    public CurrencyPrice getMaxPrice(String currencyName) {
        currencyName = currencyName.toUpperCase();
        if (currencies.contains(currencyName)) {
            List<CurrencyPrice> list = getCurrency(currencyName);
            CurrencyPrice api = getBasicObject(list);
            double max = list.get(0).getPrice();
            for (CurrencyPrice currency : list) {
                if (max < currency.getPrice()) {
                    max = currency.getPrice();
                    api.setId(currency.getId());
                    api.setCryptoCurrency(currency.getCryptoCurrency());
                    api.setFiatCurrency(currency.getFiatCurrency());
                    api.setPrice(currency.getPrice());
                }
            }
            return api;
        } else {
            throw new RuntimeException("Wrong currency");
        }
    }

    @Override
    public CurrencyPrice getMinPrice(String currencyName) {
        currencyName = currencyName.toUpperCase();
        if (currencies.contains(currencyName)) {
            List<CurrencyPrice> list = getCurrency(currencyName);
            CurrencyPrice api = getBasicObject(list);
            double min = list.get(0).getPrice();
            for (CurrencyPrice currency : list) {
                if (min > currency.getPrice()) {
                    min = currency.getPrice();
                    api.setId(currency.getId());
                    api.setCryptoCurrency(currency.getCryptoCurrency());
                    api.setFiatCurrency(currency.getFiatCurrency());
                    api.setPrice(currency.getPrice());
                }
            }
            return api;
        } else {
            throw new RuntimeException("Wrong currency");
        }
    }

    @Override
    public List<CurrencyPrice> getAll(String currencyName, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CurrencyPrice> list = currencyPriceRepository
                .findByCryptoCurrencyOrderByPriceAsc(currencyName, pageRequest);
        return list.stream().map(currencyPriceMapper::toDto).toList()
                .stream().map(currencyPriceMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public String createCsvReport() {
        fileWriter.writeDataToFile(reportCreator.createReport(getReportList()), WRITE_TO);
        return "Report done";
    }

    private List<ReportRow> getReportList() {
        List<ReportRow> reportRows = new ArrayList<>();
        for (String currency: currencies) {
            reportRows.add(getCurrencyModel(currency));
        }
        return reportRows;
    }

    private ReportRow getCurrencyModel(String currency) {
        ReportRow currencyRow = new ReportRow();
        currencyRow.setCurrencyName(currency);
        currencyRow.setMinPrice(BigDecimal.valueOf(getMinPrice(currency).getPrice()));
        currencyRow.setMaxPrice(BigDecimal.valueOf(getMaxPrice(currency).getPrice()));
        return currencyRow;
    }

    private List<CurrencyPrice> getCurrency(String currencyName) {
        return currencyPriceRepository.findByCryptoCurrencyOrderByPriceAsc(currencyName);
    }

    private CurrencyPrice getBasicObject(List<CurrencyPrice> list) {
        ApiCurrencyDto api = new ApiCurrencyDto();
        api.setId(list.get(0).getId());
        api.setCryptoCurrency(list.get(0).getCryptoCurrency());
        api.setFiatCurrency(list.get(0).getFiatCurrency());
        api.setPrice(list.get(0).getPrice());
        return currencyPriceMapper.toModel(api);
    }
}