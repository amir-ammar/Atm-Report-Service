package com.example.AtmReportService.service.parser;

import com.example.AtmReportService.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonTransactionParser implements TransactionParser {

    private static final String ATM_ID = "C";

    @Override
    public List<Transaction> parse(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        List<Transaction> transactions = mapper.readValue(
                file, mapper.getTypeFactory().constructCollectionType(List.class, Transaction.class));

        transactions.forEach(tx -> tx.setAtmId(ATM_ID));

        return transactions;
    }
}
