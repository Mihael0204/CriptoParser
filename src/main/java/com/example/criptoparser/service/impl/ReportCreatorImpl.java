package com.example.criptoparser.service.impl;

import java.util.List;
import com.example.criptoparser.model.ReportRow;
import com.example.criptoparser.service.ReportCreator;
import org.springframework.stereotype.Component;

@Component
public class ReportCreatorImpl implements ReportCreator {

    @Override
    public String createReport(List<ReportRow> reports) {
        StringBuilder contentToWrite = new StringBuilder();
        contentToWrite.append("Cryptocurrency Name, Min Price, Max Price")
                .append(System.lineSeparator());
        for (int i = 0; i < reports.size(); i++) {
            contentToWrite.append(reports.get(i).getCurrencyName())
                    .append(",")
                    .append(reports.get(i).getMinPrice())
                    .append(",")
                    .append(reports.get(i).getMaxPrice())
                    .append(System.lineSeparator());
        }
        return contentToWrite.toString();
    }
}
