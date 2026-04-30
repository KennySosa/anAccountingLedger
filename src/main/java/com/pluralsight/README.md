# Accounting Ledger Application

A Java CLI application for tracking financial transactions for personal or business use.
Built as a capstone project for the YearUP United Program.

---

## Features

- Add deposits and payments that are saved to a CSV file
- View all transactions in the ledger (newest first)
- Filter transactions by deposits or payments
- Run pre-defined financial reports:
    - Month To Date
    - Previous Month
    - Year To Date
    - Previous Year
    - Search by Vendor

---

## How to Run

### Prerequisites
- Java 17 or higher
- IntelliJ IDEA (or any Java IDE)
- Maven

### Steps
1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Navigate to `src/main/java/com/pluralsight/Main.java`
4. Run `Main.java`

---

## How to Use

| Option | Description |
|--------|-------------|
| 1 | Add a Deposit |
| 2 | Make a Payment |
| 3 | Open the Ledger |
| 4 | Exit the app |

From the Ledger you can view all entries, filter by deposits or payments, and access the Reports screen.

---

## Transaction File

All transactions are stored in:
src/main/resources/transactions.csv
Each transaction is saved in the following format:
date|time|description|vendor|amount
2025-01-15|11:15:00|Invoice 1001 paid|Joe|1500.00
2025-01-10|10:13:25|ergonomic keyboard|Amazon|-89.50

---

## Tech Used
- Java
- Maven
- IntelliJ IDEA