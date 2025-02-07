package com.example.AtmReportService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.AtmReportService.factory.TransactionParserFactory;
import com.example.AtmReportService.model.Transaction;
import com.example.AtmReportService.service.parser.TransactionParser;

import jakarta.annotation.PreDestroy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class TransactionLoader {

    private final TransactionStore transactionStore;

    @Value("${atm.data-folder}")
    private String dataFolderPath;

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

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
                List<Callable<Void>> tasks = new ArrayList<>();
                for (File file : files) {
                    tasks.add(() -> {
                        processFile(file);
                        return null;
                    });
                }

                executorService.invokeAll(tasks);
            }
            System.out.println("Transactions loaded successfully");
        } catch (Exception e) {
            System.out.println("Error loading transactions: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processFile(File file) {
        try {
            String extension = getFileExtension(file);
            TransactionParser parser = TransactionParserFactory.getParser(extension);

            if (parser == null) {
                System.out.println("No parser available for file type: " + extension);
                return;
            }

            List<Transaction> transactions = parser.parse(file);
            transactionStore.addTransactions(transactions);

        } catch (Exception e) {
            System.out.println(
                    "Error processing file " + file.getName() + ": " + e.getMessage());
        }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int dotIndex = name.lastIndexOf(".");
        if (dotIndex == -1) {
            return "";
        }
        return name.substring(dotIndex + 1).toLowerCase();
    }

    @PreDestroy
    public void shutdownExecutor() {
        executorService.shutdown();
    }
}
