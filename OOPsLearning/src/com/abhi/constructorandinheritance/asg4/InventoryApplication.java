package com.abhi.constructorandinheritance.asg4;

import java.util.Scanner;

public class InventoryApplication {

    private static Scanner scanner = new Scanner(System.in);

    public static int readInteger(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            }

            System.out.println("Invalid input");
        }
    }

    public static double readDouble(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (input.matches("\\d+(\\.\\d+)?")) {
                return Double.parseDouble(input);
            }

            System.out.println("Invalid input");
        }
    }

    public static String readString(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (input.matches("[A-Za-z]+( [A-Za-z]+)?")) {
                return input;
            }

            System.out.println("Invalid input");
        }
    }

    public static void main(String[] args) {

        InventorySystem inventorySystem = new InventorySystem(10);

        while (true) {

            System.out.println("\n===== INVENTORY MENU =====");
            System.out.println("1. Add Electronics Product");
            System.out.println("2. Add Clothing Product");
            System.out.println("3. Display Inventory");
            System.out.println("4. Process Products");
            System.out.println("5. Exit");

            int choice = readInteger("Enter choice: ");

            switch (choice) {

                case 1:

                    int electronicsId = readInteger("Enter Product ID: ");
                    String electronicsName = readString("Enter Product Name: ");
                    double electronicsPrice = readDouble("Enter Base Price: ");
                    int warrantyPeriod = readInteger("Enter Warranty Period (months): ");
                    String brand = readString("Enter Brand: ");

                    Electronics electronics = new Electronics(
                            electronicsId,
                            electronicsName,
                            electronicsPrice,
                            warrantyPeriod,
                            brand
                    );

                    inventorySystem.addProduct(electronics);
                    break;

                case 2:

                    int clothingId = readInteger("Enter Product ID: ");
                    String clothingName = readString("Enter Product Name: ");
                    double clothingPrice = readDouble("Enter Base Price: ");
                    String size = readString("Enter Size: ");
                    String fabricType = readString("Enter Fabric Type: ");

                    Clothing clothing = new Clothing(
                            clothingId,
                            clothingName,
                            clothingPrice,
                            size,
                            fabricType
                    );

                    inventorySystem.addProduct(clothing);
                    break;

                case 3:
                    inventorySystem.displayInventory();
                    break;

                case 4:
                    inventorySystem.processProducts();
                    break;

                case 5:
                    System.out.println("Exiting system");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}