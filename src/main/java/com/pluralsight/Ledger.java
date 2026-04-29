package com.pluralsight;

import java.util.Scanner;

public class Ledger {
    private Scanner scanner;
//--------- private so no other class can call to it
    public Ledger(Scanner scanner) {
        this.scanner = scanner;
    }
    public void display() {
        boolean inLedger = true;

        while (inLedger) {
            System.out.println("====== Ledger ======");
            System.out.println("1.) All Entries");
            System.out.println("2.) Deposits");
            System.out.println("3.) Payments");
            System.out.println("4.) Reports");
            System.out.println("5.) Home");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    TransactionService.printTransactions(TransactionService.loadTransactions());
                    break;
                case "2":
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getAmount() > 0)
                                    .toList()
                    );//------------- using .filter checks if the amount is < 0, this makes it so deposits are = +amounts
                    //---------------------------------------------------- and payments are = -amounts
                    break;
                case "3":
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getAmount() < 0)//had to replace .amount to a getter to work
                                    .toList()
                    );
                    break;
                case "4":
                    Reports reports = new Reports(scanner);
                    reports.display();
                    break;
                case "5":
                    inLedger = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try a different Option.");
            }
        }
    }
}


