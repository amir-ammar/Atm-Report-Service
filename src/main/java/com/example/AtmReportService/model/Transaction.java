package com.example.AtmReportService.model;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.example.AtmReportService.service.parser.xmlhandlers.LocalDateTimeAdapter;

import jakarta.xml.bind.annotation.XmlElement;

public class Transaction {

    private String transactionId;
    private LocalDateTime transactionDate;
    private String cardNumber;
    private TransactionType transactionType;
    private double amount;
    private String destinationCardNumber;
    private String atmId;

    public Transaction() {
    }

    public Transaction(String transactionId, LocalDateTime transactionDate, String cardNumber,
            TransactionType transactionType, double amount, String destinationCardNumber, String atmId) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.cardNumber = cardNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.destinationCardNumber = destinationCardNumber;
        this.atmId = atmId;
    }

    @XmlElement
    public String getTransactionId() {
        return transactionId;
    }

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    @XmlElement
    public String getCardNumber() {
        return cardNumber;
    }

    @XmlElement
    public TransactionType getTransactionType() {
        return transactionType;
    }

    @XmlElement
    public double getAmount() {
        return amount;
    }

    public String getDestinationCardNumber() {
        return destinationCardNumber;
    }

    @XmlElement
    public String getAtmId() {
        return atmId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDestinationCardNumber(String destinationCardNumber) {
        this.destinationCardNumber = destinationCardNumber;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }

    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", atmId=" + atmId + ", cardNumber=" + cardNumber
                + ", destinationCardNumber=" + destinationCardNumber + ", transactionDate=" + transactionDate
                + ", transactionId=" + transactionId + ", transactionType=" + transactionType + "]";
    }
}