package com.pluralsight;

import java.util.Scanner;

public class Main {
    static void main() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("+++++++++++ Financial Ledger +++++++++++");
            System.out.println("+++++++++++++ Accounting ++++++++++++++");
            System.out.println("1.) Deposit ");
            System.out.println("2.) Make Payments ");
            System.out.println("3.) Ledger ");
            System.out.println("4.) Exit App ");
            System.out.println("Please choose an option");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    addDeposit(scanner);
                    break;
                case "2":
                    makePayment(scanner);
                    break;
                case "3":
                    displayLedger(scanner);
                    break;
                case "4":
                    System.out.println("Have a Great Day! ");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Option, Try again please. ");
            }
        }
        scanner.close();
    }

    static void addDeposit(Scanner scanner) {
        System.out.println("------------ Add Deposit --------------");
    }

    static void makePayment(Scanner scanner) {
        System.out.println("------------ Make Payment -------------");
    }

    static void displayLedger(Scanner scanner) {
        System.out.println("--------------- Ledger ----------------");

    }
}