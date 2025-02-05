package com.example.AtmReportService.service.parser;

import com.example.AtmReportService.model.Transaction;

import java.io.File;
import java.util.List;

public interface TransactionParser {
    List<Transaction> parse(File file) throws Exception;
}