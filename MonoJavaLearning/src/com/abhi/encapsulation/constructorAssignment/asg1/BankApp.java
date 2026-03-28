package com.abhi.encapsulation.constructorAssignment.asg1;

import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int type;
        do {
            System.out.println("Select Account Type:");
            System.out.println("1. Normal Account");
            System.out.println("2. Premium Account");
            System.out.print("Enter choice: ");
            type = scanner.nextInt();

            if (type != 1 && type != 2) {
                System.out.println("Invalid choice. Please select 1 or 2.\n");
            }

        } while (type != 1 && type != 2);

        scanner.nextLine();


        String accNumber;
        do {
            System.out.print("Enter Account Number: ");
            accNumber = scanner.nextLine();

            if (accNumber.isBlank()) {
                System.out.println("Account number cannot be empty.\n");
            }

        } while (accNumber.isBlank());


        String name;
        do {
            System.out.print("Enter Account Holder Name: ");
            name = scanner.nextLine();

            if (name.isBlank()) {
                System.out.println("Name cannot be empty.\n");
            }

        } while (name.isBlank());


        double balance;
        do {
            System.out.print("Enter Initial Balance (>= 0): ");
            balance = scanner.nextDouble();

            if (balance < 0) {
                System.out.println("Balance cannot be negative.\n");
            }

        } while (balance < 0);


        BankAccount account;

        if (type == 2) {
            account = new PremiumAccount(accNumber, name, balance);
        } else {
            account = new BankAccount(accNumber, name, balance);
        }


        System.out.println("\nAccount Created Successfully!");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Holder: " + account.getAccountHolderName());
        System.out.println("Balance: ₹" + account.getBalance());
        System.out.println("Interest Rate: " + BankAccount.getInterestRate());

        scanner.close();
    }
}
