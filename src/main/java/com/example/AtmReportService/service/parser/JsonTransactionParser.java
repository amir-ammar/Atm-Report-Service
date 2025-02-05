package com.example.AtmReportService.service.parser;

import com.example.AtmReportService.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonTransactionParser implements TransactionParser {

    @Override
    public List<Transaction> parse(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Transaction.class));
    }
}
