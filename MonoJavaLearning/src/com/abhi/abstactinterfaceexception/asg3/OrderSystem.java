package com.abhi.abstactinterfaceexception.asg3;

import java.util.Scanner;
import java.util.regex.Pattern;

public class OrderSystem {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Enter number of orders: ");
        int size = scanner.nextInt();
        scanner.nextLine();

        Order[] orders = new Order[size];
        int count = 0;
        int choice;

        do {

            System.out.println("\n1 Standard Order");
            System.out.println("2 Express Order");
            System.out.println("3 International Order");
            System.out.println("4 View Orders");
            System.out.println("5 Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        if (isFull(count, orders)) break;
                        orders[count++] = new StandardOrder(
                                validateId(),
                                validateName(),
                                validateAmount());
                        break;

                    case 2:
                        if (isFull(count, orders)) break;
                        orders[count++] = new ExpressOrder(
                                validateId(),
                                validateName(),
                                validateAmount());
                        break;

                    case 3:
                        if (isFull(count, orders)) break;
                        orders[count++] = new InternationalOrder(
                                validateId(),
                                validateName(),
                                validateAmount());
                        break;

                    case 4:

                        if (count == 0) {
                            System.out.println("No orders available");
                            break;
                        }

                        for (int i = 0; i < count; i++) {

                            orders[i].displayOrder();

                            if (orders[i].verifyOrder()) {
                                System.out.println("Order Verified");
                                System.out.println("Total Amount: " + orders[i].processOrder());
                            } else {
                                System.out.println("Order Rejected");
                            }

                            System.out.println();
                        }
                        break;

                    case 5:
                        System.out.println("Exit");
                        break;

                    default:
                        System.out.println("Invalid Choice");
                }

            } catch (InvalidOrderException e) {
                System.out.println(e.getMessage());
            }

        } while (choice != 5);
    }

    static String validateId() {
        while (true) {
            System.out.print("Enter Order ID: ");
            String id = scanner.nextLine();
            if (Pattern.matches("[A-Z]{2}\\d{3}", id)) return id;
            System.out.println("Invalid ID");
        }
    }

    static String validateName() {
        while (true) {
            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine();
            if (Pattern.matches("[A-Za-z ]+", name)) return name;
            System.out.println("Invalid Name");
        }
    }

    static double validateAmount() {
        while (true) {
            try {
                System.out.print("Enter Order Amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                if (amount > 0) return amount;

            } catch (Exception e) {
                scanner.nextLine();
            }

            System.out.println("Invalid Amount");
        }
    }

    static boolean isFull(int count, Order[] orders) {
        if (count >= orders.length) {
            System.out.println("Order storage full");
            return true;
        }
        return false;
    }
}