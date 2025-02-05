package com.example.AtmReportService.service.parser;

import com.example.AtmReportService.model.Transaction;
import com.example.AtmReportService.model.TransactionType;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvTransactionParser implements TransactionParser {
    @Override
    public List<Transaction> parse(File file) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        try (CSVParser parser = new CSVParser(new FileReader(file), CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord record : parser) {
                Transaction transaction = new Transaction(
                        record.get("transactionId"),
                        LocalDateTime.parse(record.get("transactionDate")),
                        record.get("cardNumber"),
                        TransactionType.valueOf(record.get("transactionType")),
                        Double.parseDouble(record.get("amount")),
                        record.get("destinationCardNumber"),
                        "B");
                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
