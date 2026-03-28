package com.abhi.abstractclass.assignment.asg1;

import java.util.Scanner;

public class DigitalPaymentApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int totalPayments = readValidSize(scanner);
        Payment[] paymentArray = new Payment[totalPayments];
        int index = 0;

        while (true) {

            System.out.println("\n===== Digital Payment System =====");
            System.out.println("1. Credit Card Payment");
            System.out.println("2. UPI Payment");
            System.out.println("3. Wallet Payment");
            System.out.println("4. Process All Payments");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");


            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter numeric value : ");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    if (checkPaymentLimit(index, totalPayments)) break;
                    double creditAmount = readValidAmount(scanner);
                    paymentArray[index++] = new CreditCardPayment(creditAmount);
                    System.out.println("Credit Card Payment Added Successfully.");
                    break;

                case 2:
                    if (checkPaymentLimit(index, totalPayments)) break;
                    double upiAmount = readValidAmount(scanner);
                    paymentArray[index++] = new UPIPayment(upiAmount);
                    System.out.println("UPI Payment Added Successfully.");
                    break;

                case 3:
                    if (checkPaymentLimit(index, totalPayments)) break;
                    double walletAmount = readValidAmount(scanner);
                    paymentArray[index++] = new WalletPayment(walletAmount);
                    System.out.println("Wallet Payment Added Successfully.");
                    break;

                case 4:
                    if (index == 0) {
                        System.out.println("No payments available to process.");
                    } else {
                        System.out.println("\nProcessing All Payments...\n");
                        for (int i = 0; i < index; i++) {
                            paymentArray[i].processPayment();
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting Digital Payment System...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option! Please enter valid option from 1 to 5.");
            }
        }
    }


    private static double readValidAmount(Scanner scanner) {

        while (true) {

            System.out.print("Enter Payment Amount (> 0): ");

            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid input! Enter numeric value only.");
                scanner.next();
                continue;
            }

            double amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("Amount must be greater than 0. Please enter again.");
            } else {
                return amount;
            }
        }
    }


    private static int readValidSize(Scanner scanner) {

        while (true) {

            System.out.print("Enter total number of payments (> 0): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter numeric value only.");
                scanner.next();
                continue;
            }

            int number = scanner.nextInt();
            scanner.nextLine();

            if (number <= 0) {
                System.out.println("Number must be greater than 0. Please enter again.");
            } else {
                return number;
            }
        }
    }

    private static boolean checkPaymentLimit(int index, int totalPayments) {
        if (index >= totalPayments) {
            System.out.println("Payment limit reached! First process existing payments before adding new ones.");
            return true;
        }
        return false;
    }
}

