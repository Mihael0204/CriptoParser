package com.example.criptoparser.service;

import com.example.criptoparser.model.ReportRow;
import java.util.List;

public interface ReportCreator {
    String createReport(List<ReportRow> reports);
}
