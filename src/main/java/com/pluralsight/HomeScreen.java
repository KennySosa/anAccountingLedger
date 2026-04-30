package com.pluralsight;

import java.util.Scanner;

public class HomeScreen {
    private Scanner scanner;
//-----dis the constructor for Main.java
    public HomeScreen(Scanner scanner) {
        this.scanner = scanner;
    }
// so my last itteration of this had an issue of always  creating a new ledger object when picking option 3.
    //so i had to change it ledger(scanner) cuz its wasting memory on top of creating new obj repeatedly.

    public void display() {
        boolean running = true;
        Ledger ledger = new Ledger(scanner);//------ this was changed to ledger scanner

//i had 2 while (running) loops but only need one so i cut that out

        while (running) {
            System.out.println("+++++++++++ Financial Ledger +++++++++++");
            System.out.println("+++++++++++++ Accounting +++++++++++++");
            System.out.println("1.) Deposit");
            System.out.println("2.) Make Payments");
            System.out.println("3.) Ledger");
            System.out.println("4.) Exit App");
            System.out.println("Please choose an option");

            String choice = scanner.nextLine().trim();
//-----------this switch routes users to the right feature
            switch (choice) {
                case "1":
                    TransactionService.addDeposit(scanner);
                    break;
                case "2":
                    TransactionService.makePayment(scanner);
                    break;
                case "3":
                    ledger.display();
                    break;
                case "4":
                    System.out.println("Have a Great Day!");
                    running = false;//------ exit the loop++++++++++++++++++++++++++++++++++++++++++++++
                    break;
                default:
                    System.out.println("Invalid Option, Try again please.");
            }
        }
    }
}//----------------- now this code should be clean and easy to read, i guess i just need to label stuff
//------------------ just so i can go back and refer to some things for prestation or future self.