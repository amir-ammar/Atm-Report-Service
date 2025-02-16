package com.example.AtmReportService.model;

public class TransactionResponse {
    private int totalTransactions;
    private double totalAmount;

    public TransactionResponse(int totalTransactions, double totalAmount) {
        this.totalTransactions = totalTransactions;
        this.totalAmount = totalAmount;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
