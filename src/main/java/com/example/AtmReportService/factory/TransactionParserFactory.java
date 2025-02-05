package com.example.AtmReportService.factory;

import com.example.AtmReportService.model.FileExtension;
import com.example.AtmReportService.service.parser.CsvTransactionParser;
import com.example.AtmReportService.service.parser.JsonTransactionParser;
import com.example.AtmReportService.service.parser.TransactionParser;
import com.example.AtmReportService.service.parser.XmlTransactionParser;
import com.example.AtmReportService.service.parser.YamlTransactionParser;

public class TransactionParserFactory {
    public static TransactionParser getParser(String fileExtension) {
        FileExtension extension = FileExtension.fromString(fileExtension);
        return switch (extension) {
            case XML -> new XmlTransactionParser();
            case CSV -> new CsvTransactionParser();
            case JSON -> new JsonTransactionParser();
            case YAML -> new YamlTransactionParser();
            default -> throw new IllegalArgumentException("Unknown ATM ID");
        };
    }
}
