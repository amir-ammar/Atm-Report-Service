package com.example.AtmReportService.service.parser.xmlhandlers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.AtmReportService.model.Transaction;

import java.util.List;

@XmlRootElement(name = "transactions")
public class TransactionListWrapper {

    private List<Transaction> transactions;

    @XmlElement(name = "transaction")
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}