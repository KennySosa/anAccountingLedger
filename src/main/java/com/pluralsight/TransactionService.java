package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TransactionService {

    private static final String TRANSACTIONS_FILE = "src/main/resources/transactions.csv";

    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(TRANSACTIONS_FILE);
        if (!file.exists()) {
            System.out.println("No transaction file found. Starting fresh.");
            return transactions; // returns empty list instead of crashing
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // SPLIT on pipe character - gives us the 5 CSV columns
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    // PARSE - converts each string piece to the correct data type
                    LocalDate date = LocalDate.parse(parts[0].trim());
                    LocalTime time = LocalTime.parse(parts[1].trim());
                    String description = parts[2].trim();
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim());
                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
        } catch (IOException e) {
            System.out.println("Couldn't read transactions file: " + e.getMessage());
        }

        Collections.reverse(transactions);//--newest items is supposed to be first but csv does oldest instead so
        //.reverse just flips it.
        return transactions;
    }

    public static void printTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No Transactions found.");
            return;
        }
        System.out.println("\n--- Transactions ---");
        for (Transaction t : transactions) {
            // calls toString() on each Transaction automatically
            System.out.println(t);
        }
    }

    public static void addDeposit(Scanner scanner) {
        System.out.println("-------------- Deposit -------------------");

        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine().trim();

        System.out.print("Enter amount: ");
        // FIXED: removed duplicate declaration, try/catch handles bad input
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a number.");
            return; // sends user back to the menu instead of crashing
        }

        //------ so user has to deposit +amounts, and not scam me with -amounts
        amount = Math.abs(amount);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0); // cleans up nanoseconds

        // String.format keeps amount to 2 decimal places e.g. 234054523.44
        String line = date + "|" + time + "|" + description + "|" + vendor + "|" + String.format("%.2f", amount);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
            writer.newLine();//----------append true makes it so it adds to the file instead of overwriting everything.
            writer.write(line);
            System.out.println("Deposit saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving deposit: " + e.getMessage());
        }
    }

    public static void makePayment(Scanner scanner) {
        System.out.println("-------------- Payments ------------------");

        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine().trim();

        System.out.print("Enter amount: ");
        // FIXED: removed duplicate declaration, try/catch handles bad input
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a number.");
            return; // sends user back to the menu instead of crashing
        }

        //---------- this makes it so only -amounts will be considered payments
        amount = -Math.abs(amount);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0);

        // String.format keeps amount to 2 decimal
        String line = date + "|" + time + "|" + description + "|" + vendor + "|" + String.format("%.2f", amount);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
            writer.newLine();
            writer.write(line);
            System.out.println("Payment saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving payment: " + e.getMessage());
        }
    }
}