package com.example.AtmReportService.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AtmReportService.model.Transaction;
import java.time.LocalDate;

@Service
public class TransactionService {

    private final TransactionStore transactionStore;

    @Autowired
    public TransactionService(TransactionStore transactionStore) {
        this.transactionStore = transactionStore;
    }

    public List<Transaction> getTransactionsForDate(LocalDate date) {
        return transactionStore.getTransactionsForDate(date);
    }

    public List<Transaction> getDailyTransactionsPerAtm(LocalDate date, String atmId) {
        return transactionStore.getTransactionsForAtm(date, atmId);
    }

    public List<Transaction> getDailyTransactionsPerType(LocalDate date, String transactionType) {
        return transactionStore.getTransactionsForType(date, transactionType);
    }

    public Map<String, Object> getTransactionSummary(LocalDate date) {
        return transactionStore.getSummaryForDate(date);
    }

    // public List<Transaction> getTransactionsByAtm(LocalDate date, String atmId) {
    // return transactionStore.getTransactionsByAtm(date, atmId);
    // }

    // public List<Transaction> getTransactionsByType(LocalDate date, String
    // transactionType) {
    // return transactionStore.getTransactionsByType(date, transactionType);
    // }
}
