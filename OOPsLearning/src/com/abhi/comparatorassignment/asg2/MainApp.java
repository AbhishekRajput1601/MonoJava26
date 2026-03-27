package com.abhi.comparatorassignment.asg2;

import java.util.Date;
import java.util.Scanner;

public class MainApp {

    private static Scanner scanner = new Scanner(System.in);
    private static OrderManager manager = new OrderManager();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== ORDER SYSTEM MENU =====");
            System.out.println("1 Add Regular Order");
            System.out.println("2 Add Priority Order");
            System.out.println("3 Process Order");
            System.out.println("4 Display Orders");
            System.out.println("5 Sort by Amount");
            System.out.println("6 Sort by Date");
            System.out.println("7 Exit");

            System.out.print("Select your choice : ");
            choice = getIntInput();

            switch (choice) {
                case 1:
                    addRegular();
                    break;
                case 2:
                    addPriority();
                    break;
                case 3:
                    manager.processNext();
                    break;
                case 4:
                    manager.displayAll();
                    break;
                case 5:
                    manager.sortByAmount();
                    break;
                case 6:
                    manager.sortByDate();
                    break;
                case 7:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 7);
    }

    private static int getIntInput() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= 1 && value <= 7) {
                    return value;
                } else {
                    System.out.print("Enter number between 1 and 7: ");
                }
            } catch (Exception e) {
                System.out.print("Invalid input. Enter number: ");
            }
        }
    }

    private static int getAnyInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Enter valid number: ");
            }
        }
    }

    private static double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Enter valid amount: ");
            }
        }
    }

    private static String getStringInput() {
        while (true) {
            String input = scanner.nextLine();
            if (input != null && input.trim().length() >= 2) {
                return input;
            } else {
                System.out.print("Enter valid text: ");
            }
        }
    }

    private static void addRegular() {
        System.out.print("Enter Order ID: ");
        String id = getStringInput();

        System.out.print("Enter Customer Name: ");
        String name = getStringInput();

        System.out.print("Enter Amount: ");
        double amount = getDoubleInput();

        System.out.print("Enter Shipping Days: ");
        int days = getAnyInt();

        manager.addOrder(new RegularOrder(id, name, amount, new Date(), days));
        System.out.println("Added sucessfully");
    }

    private static void addPriority() {
        System.out.print("Enter Order ID: ");
        String id = getStringInput();

        System.out.print("Enter Customer Name: ");
        String name = getStringInput();

        System.out.print("Enter Amount: ");
        double amount = getDoubleInput();

        System.out.print("Enter Priority Level: ");
        int level = getAnyInt();

        System.out.print("Enter Express Fee: ");
        double fee = getDoubleInput();

        manager.addOrder(new PriorityOrder(id, name, amount, new Date(), level, fee));
        System.out.println("Added sucessfully");
    }
}