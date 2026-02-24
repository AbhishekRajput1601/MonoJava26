package com.abhi.encapsulation.test;

import java.util.Scanner;
import com.abhi.encapsulation.module.BankAccount;

public class BankApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String name;

        do {
            System.out.print("Enter Account Holder Name: ");
            name = scanner.nextLine();

            if (name == null || name.isBlank()) {
                System.out.println("Name cannot be empty. Please enter again.\n");
            }

        } while (name == null || name.isBlank());


        BankAccount account = new BankAccount(name);

        int choice;

        do {
            System.out.println("\n===== Banking Menu =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;

                case 4:
                    System.out.println("Account Number: " + account.getAccountNumber());
                    System.out.println("Account Holder: " + account.getAccountHolderName());
                    System.out.println("Account Balance: " + account.getBalance());
                    break;

                case 5:
                    System.out.println("Thank you for banking with us!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
