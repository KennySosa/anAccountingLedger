package com.pluralsight;

import java.time.LocalDate;
import java.util.Scanner;

public class Reports {
    private Scanner scanner;

    public Reports(Scanner scanner) {
        this.scanner = scanner;
    }

    public void display() {
        boolean inReports = true;

        while (inReports) {
            System.out.println("\n====== Reports ======");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            LocalDate now = LocalDate.now();

            switch (choice) {
                case "1":
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getDate().getMonth() == now.getMonth()
                                            && t.getDate().getYear() == now.getYear())
                                    .toList()
                    );
                    break;
                case "2":
                    LocalDate prevMonth = now.minusMonths(1);
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getDate().getMonth() == prevMonth.getMonth()
                                            && t.getDate().getYear() == prevMonth.getYear())
                                    .toList()
                    );
                    break;
                case "3":
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getDate().getYear() == now.getYear())
                                    .toList()
                    );
                    break;
                case "4":
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getDate().getYear() == now.getYear() - 1)
                                    .toList()
                    );
                    break;
                case "5":
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine().trim().toLowerCase();
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getVendor().toLowerCase().contains(vendor))
                                    .toList()
                    );
                    break;
                case "0":
                    inReports = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}