package com.example.AtmReportService.service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import com.example.AtmReportService.model.Transaction;
import com.example.AtmReportService.model.TransactionSummary;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class TransactionStore {
    private final Map<LocalDate, AtomicReference<TransactionSummary>> dailySummary = new ConcurrentHashMap<>();
    private final Map<LocalDate, Map<String, AtomicReference<TransactionSummary>>> atmSummary = new ConcurrentHashMap<>();
    private final Map<LocalDate, Map<String, AtomicReference<TransactionSummary>>> typeSummary = new ConcurrentHashMap<>();

    public void addTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            LocalDate date = transaction.getTransactionDate().toLocalDate();
            double amount = transaction.getAmount();
            String atmId = transaction.getAtmId();
            String type = transaction.getTransactionType().toString();

            dailySummary.computeIfAbsent(date, k -> new AtomicReference<>(new TransactionSummary()))
                    .set(dailySummary.get(date).get().addTransaction(amount));

            atmSummary.computeIfAbsent(date, k -> new ConcurrentHashMap<>())
                    .computeIfAbsent(atmId, k -> new AtomicReference<>(new TransactionSummary()))
                    .set(atmSummary.get(date).get(atmId).get().addTransaction(amount));

            typeSummary.computeIfAbsent(date, k -> new ConcurrentHashMap<>())
                    .computeIfAbsent(type, k -> new AtomicReference<>(new TransactionSummary()))
                    .set(typeSummary.get(date).get(type).get().addTransaction(amount));
        }
    }

    public TransactionSummary getDailySummary(LocalDate date) {
        return dailySummary.getOrDefault(date, new AtomicReference<>(new TransactionSummary())).get();
    }

    public TransactionSummary getAtmSummary(LocalDate date, String atmId) {
        return atmSummary.getOrDefault(date, Collections.emptyMap())
                .getOrDefault(atmId, new AtomicReference<>(new TransactionSummary())).get();
    }

    public TransactionSummary getTypeSummary(LocalDate date, String type) {
        return typeSummary.getOrDefault(date, Collections.emptyMap())
                .getOrDefault(type, new AtomicReference<>(new TransactionSummary())).get();
    }
}
