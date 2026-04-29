package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//------------- this "acts" as the front door of app
        Scanner scanner = new Scanner(System.in);
        HomeScreen homeScreen = new HomeScreen(scanner);
        homeScreen.display();
        scanner.close();

    }
}