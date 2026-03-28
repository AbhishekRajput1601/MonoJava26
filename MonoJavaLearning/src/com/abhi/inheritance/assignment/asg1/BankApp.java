package com.abhi.inheritance.assignment.asg1;

import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SavingsAccount savingsAccount = null;
        CurrentAccount currentAccount = null;

        int choice = 0;

        while (choice != 9) {

            System.out.println("\n1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Deposit in Savings");
            System.out.println("4. Withdraw from Savings");
            System.out.println("5. Deposit in Current");
            System.out.println("6. Withdraw from Current");
            System.out.println("7. View Savings Account Details");
            System.out.println("8. View Current Account Details");
            System.out.println("9. Exit");

            choice = readMenuChoice(scanner);

            switch (choice) {

                case 1:
                    String savingsHolderName = readValidName(scanner, "Enter Holder Name: ");
                    double savingsBalance = readPositiveDouble(scanner, "Enter Initial Balance: ");
                    double minimumBalance = readPositiveDouble(scanner, "Enter Minimum Balance: ");

                    savingsAccount =
                            new SavingsAccount(savingsHolderName, savingsBalance, minimumBalance);

                    System.out.println("Savings Account Created Successfully");
                    savingsAccount.displayDetails();
                    break;

                case 2:
                    String currentHolderName = readValidName(scanner, "Enter Holder Name: ");
                    double currentBalance = readPositiveDouble(scanner, "Enter Initial Balance: ");
                    double overdraftLimit = readPositiveDouble(scanner, "Enter Overdraft Limit: ");

                    currentAccount =
                            new CurrentAccount(currentHolderName, currentBalance, overdraftLimit);

                    System.out.println("Current Account Created Successfully");
                    currentAccount.displayDetails();
                    break;

                case 3:
                    if (savingsAccount == null) {
                        System.out.println("Savings Account not created");
                        break;
                    }
                    double depositSavings = readPositiveDouble(scanner, "Enter Amount: ");
                    savingsAccount.deposit(depositSavings);
                    break;

                case 4:
                    if (savingsAccount == null) {
                        System.out.println("Savings Account not created");
                        break;
                    }
                    double withdrawSavings = readPositiveDouble(scanner, "Enter Amount: ");
                    savingsAccount.withdraw(withdrawSavings);
                    break;

                case 5:
                    if (currentAccount == null) {
                        System.out.println("Current Account not created");
                        break;
                    }
                    double depositCurrent = readPositiveDouble(scanner, "Enter Amount: ");
                    currentAccount.deposit(depositCurrent);
                    break;

                case 6:
                    if (currentAccount == null) {
                        System.out.println("Current Account not created");
                        break;
                    }
                    double withdrawCurrent = readPositiveDouble(scanner, "Enter Amount: ");
                    currentAccount.withdraw(withdrawCurrent);
                    break;

                case 7:
                    if (savingsAccount == null) {
                        System.out.println("Savings Account not created");
                        break;
                    }
                    savingsAccount.displayDetails();
                    break;

                case 8:
                    if (currentAccount == null) {
                        System.out.println("Current Account not created");
                        break;
                    }
                    currentAccount.displayDetails();
                    break;

                case 9:
                    System.out.println("Exiting Program");
                    break;

                default:
                    System.out.println("Invalid input, please try again");
            }
        }
    }

    private static double readPositiveDouble(Scanner scanner, String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input, please try again");
            scanner.next();
            System.out.print(message);
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        while (value <= 0) {
            System.out.println("Invalid input, please try again");
            System.out.print(message);
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input, please try again");
                scanner.next();
                System.out.print(message);
            }
            value = scanner.nextDouble();
            scanner.nextLine();
        }
        return value;
    }

    private static int readMenuChoice(Scanner scanner) {
        System.out.print("Enter Choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input, please try again");
            scanner.next();
            System.out.print("Enter Choice: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static String readValidName(Scanner scanner, String message) {
        System.out.print(message);
        String name = scanner.nextLine();

        while (name == null || name.isBlank() || !name.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
            System.out.println("Invalid input, please try again");
            System.out.print(message);
            name = scanner.nextLine();
        }

        return name;
    }
}
