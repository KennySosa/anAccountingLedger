package com.pluralsight;

import java.util.Scanner;

public class HomeScreen {
    private Scanner scanner;

    public HomeScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public void display() {
        boolean running = true;

        while (running) {
            System.out.println("+++++++++++ Financial Ledger +++++++++++");
            System.out.println("+++++++++++++ Accounting +++++++++++++");
            System.out.println("1.) Deposit");
            System.out.println("2.) Make Payments");
            System.out.println("3.) Ledger");
            System.out.println("4.) Exit App");
            System.out.println("Please choose an option");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    TransactionService.addDeposit(scanner);
                    break;
                case "2":
                    TransactionService.makePayment(scanner);
                    break;
                case "3":
                    Ledger ledger = new Ledger(scanner);
                    ledger.display();
                    break;
                case "4":
                    System.out.println("Have a Great Day!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option, Try again please.");
            }
        }
    }
}