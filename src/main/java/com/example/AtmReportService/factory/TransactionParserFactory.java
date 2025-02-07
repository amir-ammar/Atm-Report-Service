package com.example.AtmReportService.factory;

import com.example.AtmReportService.model.AtmFileType;
import com.example.AtmReportService.service.parser.*;

public class TransactionParserFactory {
    public static TransactionParser getParser(String fileExtension) {
        AtmFileType atmType = AtmFileType.fromExtension(fileExtension);
        if (atmType == null) {
            throw new IllegalArgumentException("Unknown file extension: " + fileExtension);
        }

        return switch (atmType) {
            case ATM_A -> new XmlTransactionParser();
            case ATM_B -> new CsvTransactionParser();
            case ATM_C -> new JsonTransactionParser();
            case ATM_D -> new YamlTransactionParser();
        };
    }
}
