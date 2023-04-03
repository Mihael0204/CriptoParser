package com.example.criptoparser.service;

import java.util.List;
import com.example.criptoparser.model.ReportRow;

public interface ReportCreator {
    String createReport(List<ReportRow> reports);
}
