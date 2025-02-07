package com.example.AtmReportService.service.parser;

import com.example.AtmReportService.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class YamlTransactionParser implements TransactionParser {

    private static final String ATM_ID = "D";

    @Override
    public List<Transaction> parse(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.registerModule(new JavaTimeModule());
        try (FileReader reader = new FileReader(file)) {
            List<Transaction> transactions = mapper.readValue(reader,
                    mapper.getTypeFactory().constructCollectionType(List.class, Transaction.class));

            transactions.forEach(tx -> tx.setAtmId(ATM_ID));

            return transactions;
        }
    }
}
