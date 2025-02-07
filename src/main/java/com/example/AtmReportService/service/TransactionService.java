package com.example.AtmReportService.service;

import com.example.AtmReportService.model.TransactionResponse;
import com.example.AtmReportService.model.TransactionSummary;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class TransactionService {
    private final TransactionStore transactionStore;

    public TransactionService(TransactionStore transactionStore) {
        this.transactionStore = transactionStore;
    }

    public TransactionResponse getDailySummary(LocalDate date) {
        return toResponse(transactionStore.getDailySummary(date));
    }

    public TransactionResponse getAtmSummary(LocalDate date, String atmId) {
        return toResponse(transactionStore.getAtmSummary(date, atmId));
    }

    public TransactionResponse getTypeSummary(LocalDate date, String type) {
        return toResponse(transactionStore.getTypeSummary(date, type));
    }

    private TransactionResponse toResponse(TransactionSummary summary) {
        return new TransactionResponse(summary.getTotalTransactions(), summary.getTotalAmount());
    }

    public static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD");
        }
    }
}
