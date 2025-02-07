package com.example.AtmReportService.model;

public class TransactionSummary {
    private int totalTransactions;
    private double totalAmount;

    public TransactionSummary() {
        this.totalTransactions = 0;
        this.totalAmount = 0.0;
    }

    public void addTransaction(double amount) {
        this.totalTransactions++;
        this.totalAmount += amount;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
