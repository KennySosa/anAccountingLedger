package com.pluralsight;

import java.time.LocalDate;
import java.util.Scanner;

public class Reports {
    private Scanner scanner;
//---------- the construct when new reports are used in the ledger
    public Reports(Scanner scanner) {
        this.scanner = scanner;
    }
//---------- start of the report menu loop
    public void display() {
        boolean inReports = true;
        LocalDate now = LocalDate.now();//----- for todays date. this was in the loop at first but would call on it every
        //single time so moviing it up here could save some memory but i can always put in the loop if i want.

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

            switch (choice) {
                case "1"://------------- matches month and current year
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getDate().getMonth() == now.getMonth()
                                            && t.getDate().getYear() == now.getYear())
                                    .toList()
                    );
                    break;
                case "2"://------------- time machine, goes back 1 month from today
                    LocalDate prevMonth = now.minusMonths(1);
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getDate().getMonth() == prevMonth.getMonth()
                                            && t.getDate().getYear() == prevMonth.getYear())
                                    .toList()
                    );
                    break;
                case "3"://--------- matches any transaction that happened this year
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getDate().getYear() == now.getYear())
                                    .toList()
                    );
                    break;
                case "4"://---------- gets last years number
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getDate().getYear() == now.getYear() - 1)
                                    .toList()
                    );
                    break;
                case "5":
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine().trim().toLowerCase();
                    if (vendor.isEmpty()) {//--- so i didnt add this part til now but if the user hit enter while it was empty
                        System.out.println("Please enter a vendor name.");//it would crash.
                        break;
                    }
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