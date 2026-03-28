package com.abhi.constructorandinheritance.asg1;

import java.util.Scanner;

public class BankApplication {

    private static Scanner scanner = new Scanner(System.in);

    public static int readInteger(String message) {

        while (true) {
            System.out.print(message);
            String inputValue = scanner.nextLine();

            if (inputValue.matches("\\d+")) {
                return Integer.parseInt(inputValue);
            }

            System.out.println("Invalid input.");
        }
    }

    public static double readDouble(String message) {

        while (true) {
            System.out.print(message);
            String inputValue = scanner.nextLine();

            if (inputValue.matches("\\d+(\\.\\d+)?")) {
                return Double.parseDouble(inputValue);
            }

            System.out.println("Invalid input.");
        }
    }

    public static String readName(String message) {

        while (true) {
            System.out.print(message);
            String inputValue = scanner.nextLine();

            if (inputValue.matches("[A-Za-z]+( [A-Za-z]+)?")) {
                return inputValue;
            }

            System.out.println("Invalid name.");
        }
    }

    public static void main(String[] args) {

        BankSystem bankSystem = new BankSystem(10);

        while (true) {

            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Display All Accounts");
            System.out.println("4. Exit");

            int choice = readInteger("Enter choice: ");

            switch (choice) {

                case 1:

                    int savingsAccountNumber = readInteger("Enter Account Number: ");
                    String savingsHolderName = readName("Enter Holder Name: ");
                    double savingsBalance = readDouble("Enter Initial Balance: ");
                    double interestRate = readDouble("Enter Interest Rate: ");

                    SavingsAccount savingsAccount =
                            new SavingsAccount(
                                    savingsAccountNumber,
                                    savingsHolderName,
                                    savingsBalance,
                                    interestRate
                            );

                    bankSystem.addAccount(savingsAccount);
                    break;

                case 2:

                    int currentAccountNumber = readInteger("Enter Account Number: ");
                    String currentHolderName = readName("Enter Holder Name: ");
                    double currentBalance = readDouble("Enter Balance: ");
                    double overdraftLimit = readDouble("Enter Overdraft Limit: ");

                    CurrentAccount currentAccount =
                            new CurrentAccount(
                                    currentAccountNumber,
                                    currentHolderName,
                                    currentBalance,
                                    overdraftLimit
                            );

                    bankSystem.addAccount(currentAccount);
                    break;

                case 3:
                    bankSystem.displayAllAccounts();
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    return;

                default:
                    System.out.println("Invalid menu option.");
            }
        }
    }
}