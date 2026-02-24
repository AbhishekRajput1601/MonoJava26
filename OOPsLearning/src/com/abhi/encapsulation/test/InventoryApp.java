package com.abhi.encapsulation.test;

import java.util.Scanner;
import com.abhi.encapsulation.module.Product;

public class InventoryApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String name;
        int price;
        int stock;

        do {
            System.out.print("Enter Product Name: ");
            name = scanner.nextLine();

            if (name == null || name.isBlank()) {
                System.out.println("Product name cannot be empty. Please enter again.\n");
            }

        } while (name == null || name.isBlank());

        do {
            System.out.print("Enter Product Price (positive integer): ");
            price = scanner.nextInt();

            if (price <= 0) {
                System.out.println("Price must be a positive integer. Please enter again.\n");
            }

        } while (price <= 0);

        do {
            System.out.print("Enter Initial Stock (positive integer): ");
            stock = scanner.nextInt();

            if (stock <= 0) {
                System.out.println("Stock must be a positive integer. Please enter again.\n");
            }

        } while (stock <= 0);

        Product product = new Product(name, price, stock);

        int choice;

        do {
            System.out.println("\n===== Inventory Menu =====");
            System.out.println("1. View Product Details");
            System.out.println("2. Increase Stock");
            System.out.println("3. Reduce Stock (Process Order)");
            System.out.println("4. Update Price (Admin Only)");
            System.out.println("5. Discontinue Product");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Product ID: " + product.getProductId());
                    System.out.println("Name: " + product.getProductName());
                    System.out.println("Price: $" + product.getPrice());
                    System.out.println("Stock: " + product.getStockQuantity());
                    System.out.println("Discontinued: " + product.isDiscontinued());
                    break;

                case 2:
                    System.out.print("Enter quantity to increase: ");
                    int addQty = scanner.nextInt();
                    product.increaseStock(addQty);
                    break;

                case 3:
                    System.out.print("Enter quantity to reduce: ");
                    int reduceQty = scanner.nextInt();
                    product.reduceStock(reduceQty);
                    break;

                case 4:
                    System.out.print("Are you admin? (true/false): ");
                    boolean isAdmin = scanner.nextBoolean();

                    System.out.print("Enter new price: ");
                    int newPrice = scanner.nextInt();

                    if (newPrice > 0) {
                        product.updatePrice(newPrice, isAdmin);
                    } else {
                        System.out.println("Price must be positive.");
                    }
                    break;

                case 5:
                    product.discontinueProduct();
                    break;

                case 6:
                    System.out.println("Exiting Inventory System...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);

        scanner.close();
    }
}
