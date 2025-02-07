# ATM Report Service

This ATM Report Service is designed to process transaction data from different ATMs (A, B, C, D), which generate transactions in other formats (XML, CSV, JSON, YAML) daily. The service generates reports based on the transaction data and exposes them as REST APIs.

## Features

- Supports multiple transaction file formats: XML, CSV, JSON, YAML
- Processes and loads transaction data asynchronously using a thread pool
- Exposes REST APIs to get daily transaction summaries, ATM-specific summaries, and type-based summaries

## Technology Stack

- **Java 17** (or the version you're using)
- **Spring Boot** (for building the service)
- **Spring Web** (for creating REST endpoints)
- **ExecutorService** (for concurrent transaction file processing)
- **Lombok** (for reducing boilerplate code)

## Setup and Installation

### Prerequisites

- **Java 17+** installed
- **Maven** or **Gradle** for building the project

### Clone the Repository

```bash
git clone https://github.com/yourusername/Atm-Report-Service.git
cd Atm-Report-Service
```

## Suggested Dates and IDs for Testing

To test the API endpoints, you can use the following sample data:

- **Test Date**: `2025-02-05`
- **ATM IDs**: `A`, `B`, `C`, `D`
- **Transaction Types**: `WITHDRAW`, `DEPOSIT`, `BALANCE_TRANSFER`

Use these values to generate transaction summaries through the API.

## OpenAPI Documentation

The OpenAPI documentation for this service is hosted at:

http://18.205.154.180/swagger-ui/index.html
