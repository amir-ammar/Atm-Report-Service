package com.example.AtmReportService.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AtmReportService.model.Transaction;
import com.example.AtmReportService.model.TransactionType;
import com.example.AtmReportService.service.TransactionService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RestController
@RequestMapping("/api/v1/reports")
@AllArgsConstructor
@EnableCaching
public class AtmReportController {

    @Autowired
    private TransactionService transactionReportService;

    @GetMapping("/daily-transactions")
    public List<Transaction> getDailyTransactions(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return transactionReportService.getTransactionsForDate(localDate);
    }

    @GetMapping("/daily-transactions-per-atm")
    public List<Transaction> getDailyTransactionsPerAtm(@RequestParam String date,
            @RequestParam String atm) {
        LocalDate localDate = LocalDate.parse(date);
        return transactionReportService.getDailyTransactionsPerAtm(localDate, atm);
    }

    @GetMapping("/daily-transactions-per-type")
    public List<Transaction> getDailyTransactionsPerType(@RequestParam String date,
            @RequestParam String transactionType) {
        LocalDate localDate = LocalDate.parse(date);
        return transactionReportService.getDailyTransactionsPerType(localDate, transactionType);
    }

    @GetMapping("/transaction-summary")
    public Map<String, Object> getTransactionSummary(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return transactionReportService.getTransactionSummary(localDate);
    }

}