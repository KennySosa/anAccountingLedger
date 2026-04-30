package com.pluralsight;

import java.util.Scanner;

public class Ledger {
    private Scanner scanner;
//--------- private so no other class can call to it

    //constructor, called when used in home screen
    public Ledger(Scanner scanner) {
        this.scanner = scanner;
    }
    public void display() {
        boolean inLedger = true;
        Reports reports = new Reports(scanner);//--- so i actually had this in the HomeScreen class but since
        //----------------------------put it here i can just delete the HomeScreen one and keep this one(only used here)
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
                case "1"://----------- loads transaction from csv then prints them
                    TransactionService.printTransactions(TransactionService.loadTransactions());
                    break;
                case "2"://------------ this only keeps positive amounts aka:deposits
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getAmount() > 0)
                                    .toList()
                    );//------------- using .filter checks if the amount is < 0, this makes it so deposits are = +amounts
                    //---------------------------------------------------- and payments are = -amounts
                    break;
                case "3"://---------- only keeps negative amounts aka: payments
                    TransactionService.printTransactions(
                            TransactionService.loadTransactions().stream()
                                    .filter(t -> t.getAmount() < 0)//had to replace .amount to a getter to work
                                    .toList()
                    );
                    break;
                case "4"://--------- sends user to report screen when picked
                    reports.display();
                    break;
                case "5"://--------- go back to home screen button loop
                    inLedger = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try a different Option.");
            }
        }
    }
}


