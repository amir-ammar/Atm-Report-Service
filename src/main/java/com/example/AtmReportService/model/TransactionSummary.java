package com.example.AtmReportService.model;

public class TransactionSummary {
    private final int totalTransactions;
    private final double totalAmount;

    public TransactionSummary() {
        this.totalTransactions = 0;
        this.totalAmount = 0.0;
    }

    private TransactionSummary(int totalTransactions, double totalAmount) {
        this.totalTransactions = totalTransactions;
        this.totalAmount = totalAmount;
    }

    public TransactionSummary addTransaction(double amount) {
        return new TransactionSummary(this.totalTransactions + 1, this.totalAmount + amount);
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
