package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TransactionService {

    private static final String TRANSACTIONS_FILE = "src/main/resources/transactions.csv";

    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0].trim());
                    LocalTime time = LocalTime.parse(parts[1].trim());
                    String description = parts[2].trim();
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim());
                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
        } catch (IOException e) {
            System.out.println("Couldn't read transactions files" + e.getMessage());
        }

        Collections.reverse(transactions);
        return transactions;
    }

    public static void printTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No Transactions found.");
            return;
        }
        System.out.println("\n--- Transactions ---");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
    public static void addDeposit(Scanner scanner) {
        System.out.println("-------------- Deposit -------------------");
    }

    public static void makePayment(Scanner scanner) {
        System.out.println("-------------- Payments ------------------");
    }
}
