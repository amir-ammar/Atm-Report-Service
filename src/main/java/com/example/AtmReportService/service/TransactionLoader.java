package com.example.AtmReportService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.AtmReportService.factory.TransactionParserFactory;
import com.example.AtmReportService.model.Transaction;
import com.example.AtmReportService.service.parser.TransactionParser;

import java.io.File;
import java.util.List;

@Service
public class TransactionLoader {

    private final TransactionStore transactionStore;

    @Value("${atm.data-folder}")
    private String dataFolderPath;

    public TransactionLoader(TransactionStore transactionStore) {
        this.transactionStore = transactionStore;
    }

    @jakarta.annotation.PostConstruct
    public void loadTransactions() {
        try {
            File dataFolder = new File(dataFolderPath);
            File[] files = dataFolder.listFiles((dir, name) -> name.endsWith(".xml") || name.endsWith(".csv")
                    || name.endsWith(".json") || name.endsWith(".yaml"));

            if (files != null) {
                for (File file : files) {
                    String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                    TransactionParser parser = TransactionParserFactory.getParser(extension);
                    List<Transaction> transactions = parser.parse(file);
                    transactionStore.addTransactions(transactions);
                }
            }
            System.out.println("Transactions loaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
