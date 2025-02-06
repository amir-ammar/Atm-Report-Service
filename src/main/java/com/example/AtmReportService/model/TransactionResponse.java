package com.example.AtmReportService.model;

import java.util.List;

public class TransactionResponse {
    private int totalTransactions;
    private double totalAmount;
    private List<Transaction> transactions;

    public TransactionResponse(int totalTransactions, double totalAmount, List<Transaction> transactions) {
        this.totalTransactions = totalTransactions;
        this.totalAmount = totalAmount;
        this.transactions = transactions;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
