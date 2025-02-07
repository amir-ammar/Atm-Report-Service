package com.example.AtmReportService.controller;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;
import com.example.AtmReportService.model.TransactionResponse;
import com.example.AtmReportService.service.TransactionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reports")
@EnableCaching
@RequiredArgsConstructor
public class AtmReportController {

    private final TransactionService transactionService;

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
