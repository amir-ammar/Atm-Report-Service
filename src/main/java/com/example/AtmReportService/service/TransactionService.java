package com.example.AtmReportService.service;

import com.example.AtmReportService.model.Transaction;
import com.example.AtmReportService.model.TransactionResponse;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionStore transactionStore;

    public TransactionService(TransactionStore transactionStore) {
        this.transactionStore = transactionStore;
    }

    public TransactionResponse getTransactionsForDate(LocalDate date) {
        List<Transaction> transactions = transactionStore.getTransactionsForDate(date);
        return new TransactionResponse(transactions.size(), calculateTotalAmount(transactions), transactions);
    }

    public TransactionResponse getTransactionsForAtm(LocalDate date, String atmId) {
        List<Transaction> transactions = transactionStore.getTransactionsForAtm(date, atmId);
        return new TransactionResponse(transactions.size(), calculateTotalAmount(transactions), transactions);
    }

    public TransactionResponse getTransactionsForType(LocalDate date, String type) {
        List<Transaction> transactions = transactionStore.getTransactionsForType(date, type);
        return new TransactionResponse(transactions.size(), calculateTotalAmount(transactions), transactions);
    }

    private double calculateTotalAmount(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }
}
