package com.example.AtmReportService.controller;

import org.springframework.web.bind.annotation.*;
import com.example.AtmReportService.model.TransactionResponse;
import com.example.AtmReportService.service.TransactionService;

@RestController
@RequestMapping("/api/v1/reports")
public class AtmReportController {

    private final TransactionService transactionService;

    public AtmReportController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/daily")
    public TransactionResponse getDailySummary(@RequestParam String date) {
        return transactionService.getDailySummary(TransactionService.parseDate(date));
    }

    @GetMapping("/daily/atm")
    public TransactionResponse getAtmSummary(@RequestParam String date, @RequestParam String atmId) {
        return transactionService.getAtmSummary(TransactionService.parseDate(date), atmId);
    }

    @GetMapping("/daily/type")
    public TransactionResponse getTypeSummary(@RequestParam String date, @RequestParam String type) {
        return transactionService.getTypeSummary(TransactionService.parseDate(date), type);
    }

}
