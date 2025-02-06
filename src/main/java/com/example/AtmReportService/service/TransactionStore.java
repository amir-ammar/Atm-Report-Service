package com.example.AtmReportService.service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import com.example.AtmReportService.model.Transaction;

@Component
public class TransactionStore {
    private final Map<LocalDate, List<Transaction>> transactionsByDate = new ConcurrentHashMap<>();

    private final Map<LocalDate, Map<String, List<Transaction>>> transactionsByAtm = new ConcurrentHashMap<>();

    private final Map<LocalDate, Map<String, List<Transaction>>> transactionsByType = new ConcurrentHashMap<>();

    public void addTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            LocalDate date = transaction.getTransactionDate().toLocalDate();

            transactionsByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(transaction);

            transactionsByAtm
                    .computeIfAbsent(date, k -> new ConcurrentHashMap<>())
                    .computeIfAbsent(transaction.getAtmId(), k -> new ArrayList<>())
                    .add(transaction);

            transactionsByType
                    .computeIfAbsent(date, k -> new ConcurrentHashMap<>())
                    .computeIfAbsent(transaction.getTransactionType().toString(), k -> new ArrayList<>())
                    .add(transaction);
        }
    }

    public List<Transaction> getTransactionsForDate(LocalDate date) {
        return transactionsByDate.getOrDefault(date, Collections.emptyList());
    }

    public List<Transaction> getTransactionsForAtm(LocalDate date, String atmId) {
        return transactionsByAtm.getOrDefault(date, Collections.emptyMap())
                .getOrDefault(atmId, Collections.emptyList());
    }

    public List<Transaction> getTransactionsForType(LocalDate date, String type) {
        return transactionsByType.getOrDefault(date, Collections.emptyMap())
                .getOrDefault(type, Collections.emptyList());
    }

}
