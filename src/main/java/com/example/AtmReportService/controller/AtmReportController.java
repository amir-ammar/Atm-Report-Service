package com.example.AtmReportService.controller;

import java.time.LocalDate;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.AtmReportService.model.TransactionResponse;
import com.example.AtmReportService.service.TransactionService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RestController
@RequestMapping("/api/v1/reports")
@EnableCaching
public class AtmReportController {

    private final TransactionService transactionService;

    public AtmReportController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/daily")
    public TransactionResponse getDailyTransactions(@RequestParam String date) {
        System.out.println("Getting daily transactions for date: " + date);
        LocalDate localDate = LocalDate.parse(date);
        return transactionService.getTransactionsForDate(localDate);
    }

    @GetMapping("/daily/atm")
    public TransactionResponse getTransactionsForAtm(@RequestParam String date, @RequestParam String atmId) {
        LocalDate localDate = LocalDate.parse(date);
        return transactionService.getTransactionsForAtm(localDate, atmId);
    }

    @GetMapping("/daily/type")
    public TransactionResponse getTransactionsForType(@RequestParam String date, @RequestParam String type) {
        LocalDate localDate = LocalDate.parse(date);
        return transactionService.getTransactionsForType(localDate, type);
    }
}