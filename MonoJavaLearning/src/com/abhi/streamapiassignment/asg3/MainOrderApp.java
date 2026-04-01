package com.abhi.streamapiassignment.asg3;

import java.util.*;

public class MainOrderApp {

    public static void main(String[] args) {

        List<Order> orders = new ArrayList<>();

        System.out.println("=== Online Order Processing System ===");

        int n = InputValidator.getValidInt("Enter number of orders: ");

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Order " + (i + 1));

            int id = InputValidator.getValidInt("Order ID: ");
            String name = InputValidator.getValidString("Customer Name: ");
            String category = InputValidator.getValidString("Category: ");
            double amount = InputValidator.getValidDouble("Amount: ");
            String status = InputValidator.getValidString("Status (completed/cancelled/pending): ");

            orders.add(new Order(id, name, category, amount, status));
        }

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Completed Orders");
            System.out.println("2. Total Revenue");
            System.out.println("3. Group by Category");
            System.out.println("4. Maximum Order");
            System.out.println("5. Count Cancelled Orders");
            System.out.println("6. OrderId -> Amount Map");
            System.out.println("7. Sorted Customer Names");
            System.out.println("8. Exit");

            int choice = InputValidator.getValidInt("Enter choice: ");

            switch (choice) {
                case 1:
                    OrderService.getCompletedOrders(orders);
                    break;

                case 2:
                    OrderService.getTotalRevenue(orders);
                    break;

                case 3:
                    OrderService.groupByCategory(orders);
                    break;

                case 4:
                    OrderService.getMaxOrder(orders);
                    break;

                case 5:
                    OrderService.countCancelled(orders);
                    break;

                case 6:
                    OrderService.orderIdToAmountMap(orders);
                    break;

                case 7:
                    OrderService.sortedCustomerNames(orders);
                    break;

                case 8:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
