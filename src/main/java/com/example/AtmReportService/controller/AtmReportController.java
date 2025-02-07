package com.example.AtmReportService.controller;

import org.springframework.web.bind.annotation.*;
import com.example.AtmReportService.model.TransactionResponse;
import com.example.AtmReportService.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class AtmReportController {

    private final TransactionService transactionService;

    public AtmReportController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(summary = "Get Daily Transaction Report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved daily report"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    @GetMapping("/reports/daily")
    public TransactionResponse getDailySummary(
            @Parameter(description = "Date in format: YYYY-MM-DD") @RequestParam String date) {
        return transactionService.getDailySummary(TransactionService.parseDate(date));
    }

    @Operation(summary = "Get ATM Transaction Report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved ATM report"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    @GetMapping("/reports/daily/atm")
    public TransactionResponse getAtmSummary(@RequestParam String date,
            @Parameter(description = "Available ATM IDs: A, B, C, D") @RequestParam String atmId) {
        return transactionService.getAtmSummary(TransactionService.parseDate(date), atmId);
    }

    @Operation(summary = "Get Type Transaction Report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved type report"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    @GetMapping("/reports/daily/type")
    public TransactionResponse getTypeSummary(@RequestParam String date,
            @Parameter(description = "Available types: DEPOSIT, WITHDRAW, BALANCE_TRANSFER") @RequestParam String type) {
        return transactionService.getTypeSummary(TransactionService.parseDate(date), type);
    }

}
