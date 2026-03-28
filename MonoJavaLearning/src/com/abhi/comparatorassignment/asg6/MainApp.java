package com.abhi.comparatorassignment.asg6;

import java.util.Scanner;

public class MainApp {

    static Scanner scanner = new Scanner(System.in);
    static InventoryManager manager = new InventoryManager();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== INVENTORY SYSTEM =====");
            System.out.println("1. Add Electronic Product");
            System.out.println("2. Add Grocery Product");
            System.out.println("3. Add Return Request");
            System.out.println("4. Process Return");
            System.out.println("5. Display All Products");
            System.out.println("6. Display Sorted by ID");
            System.out.println("7. Display Sorted by Price");
            System.out.println("8. Display Sorted by Name");
            System.out.println("9. Display By Category");
            System.out.println("10. Remove Expensive Products");
            System.out.println("11. Exit");

            choice = getIntInput();

            switch (choice) {

                case 1:
                    addElectronicProduct();
                    break;

                case 2:
                    addGroceryProduct();
                    break;

                case 3:
                    addReturn();
                    break;

                case 4:
                    manager.processReturn();
                    break;

                case 5:
                    manager.displayAllProducts();
                    break;

                case 6:
                    manager.displaySortedById();
                    break;

                case 7:
                    manager.displaySortedByPrice();
                    break;

                case 8:
                    manager.displaySortedByName();
                    break;

                case 9:
                    System.out.print("Enter category: ");
                    String cat = scanner.next();
                    manager.displayByCategory(cat);
                    break;

                case 10:
                    System.out.print("Enter price limit: ");
                    double price = scanner.nextDouble();
                    manager.removeExpensiveProducts(price);
                    break;

                case 11:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 11);
    }

    static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter valid number");
            scanner.next();
        }
        return scanner.nextInt();
    }

    static void addElectronicProduct() {

        System.out.print("Enter ID: ");
        String id = scanner.next();

        System.out.print("Enter Name: ");
        String name = scanner.next();

        System.out.print("Enter Category: ");
        String category = scanner.next();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Warranty Months: ");
        int warranty = getIntInput();

        Product p = new ElectronicProduct(id, name, category, price, warranty);
        manager.addProduct(p);
    }

    static void addGroceryProduct() {

        System.out.print("Enter ID: ");
        String id = scanner.next();

        System.out.print("Enter Name: ");
        String name = scanner.next();

        System.out.print("Enter Category: ");
        String category = scanner.next();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Expiry Days: ");
        int expiry = getIntInput();

        Product p = new GroceryProduct(id, name, category, price, expiry);
        manager.addProduct(p);
    }

    static void addReturn() {

        System.out.print("Enter Product ID: ");
        String id = scanner.next();

        for (Product p : manager.productList) {
            if (p.getProductId().equals(id)) {
                manager.addReturnRequest(p);
                return;
            }
        }

        System.out.println("Product not found");
    }
}