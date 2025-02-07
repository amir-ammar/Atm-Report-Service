package com.example.AtmReportService.service.parser;

import com.example.AtmReportService.model.Transaction;
import com.example.AtmReportService.model.TransactionType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CsvTransactionParser implements TransactionParser {

    private static final String ATM_ID = "B";

    @Override
    public List<Transaction> parse(File file) throws IOException {
        List<Transaction> transactions = new ArrayList<>();

        try (FileReader fileReader = new FileReader(file);
                CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : parser) {
                try {
                    Transaction transaction = new Transaction(
                            record.get("transactionId"),
                            LocalDateTime.parse(record.get("transactionDate")),
                            record.get("cardNumber"),
                            TransactionType.valueOf(record.get("transactionType")),
                            Double.parseDouble(record.get("amount")),
                            record.get("destinationCardNumber"),
                            ATM_ID);
                    transactions.add(transaction);
                } catch (Exception e) {
                    throw new IOException("Error parsing CSV record: " + record, e);
                }
            }
        }
        return transactions;
    }
}
