package solid.assignment.asg1.test;

import solid.assignment.asg1.model.NotificationModel.EmailNotifier;
import solid.assignment.asg1.model.NotificationModel.Notifier;
import solid.assignment.asg1.model.NotificationModel.SMSNotifier;
import solid.assignment.asg1.model.ValuationModel.FIFOValuation;
import solid.assignment.asg1.model.ValuationModel.LIFOValuation;
import solid.assignment.asg1.model.ValuationModel.ValuationStrategy;
import solid.assignment.asg1.model.modelservice.InventoryService;
import solid.assignment.asg1.model.modelservice.Product;
import solid.assignment.asg1.model.modelservice.ReorderService;

import java.util.*;

public class InventorySystemMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        List<Notifier> notifiers = new ArrayList<>();
        notifiers.add(new EmailNotifier());
        notifiers.add(new SMSNotifier());

        ReorderService reorderService = new ReorderService();

        ValuationStrategy valuationStrategy = chooseValuationStrategy();

        InventoryService inventoryService = new InventoryService(
                notifiers,
                reorderService,
                valuationStrategy
        );

        while (true) {
            printMenu();
            int choice = getValidInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addProduct(inventoryService);
                    break;
                case 2:
                    removeStock(inventoryService);
                    break;
                case 3:
                    inventoryService.calculateInventoryValue();
                    break;
                case 4:
                    inventoryService.showAllProducts();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Inventory Management System =====");
        System.out.println("1. Add Product");
        System.out.println("2. Remove Stock");
        System.out.println("3. Calculate Inventory Value");
        System.out.println("4. Show All Products"); // NEW
        System.out.println("5. Exit");
    }

    private static void addProduct(InventoryService service) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        int qty = getValidInt("Enter quantity: ");
        int reorderLevel = getValidInt("Enter reorder level: ");
        double price = getValidDouble("Enter price: ");

        Product product = new Product(name, qty, reorderLevel, price);
        service.addProduct(product);

        System.out.println("Product added successfully!");
    }

    private static void removeStock(InventoryService service) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        int qty = getValidInt("Enter quantity to remove: ");
        service.removeStock(name, qty);
    }

    private static int getValidInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine());

                if (value < 0) {
                    System.out.println("Value cannot be negative.");
                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid integer.");
            }
        }
    }

    private static double getValidDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine());

                if (value < 0) {
                    System.out.println("Value cannot be negative.");
                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
    }

    private static ValuationStrategy chooseValuationStrategy() {
        System.out.println("Choose Valuation Strategy:");
        System.out.println("1. FIFO");
        System.out.println("2. LIFO");

        int choice = 0;

        while (choice != 1 && choice != 2) {
            choice = getValidInt("Enter choice (1 or 2): ");

            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice. Try again.");
            }
        }

        if (choice == 1) {
            return new FIFOValuation();
        } else {
            return new LIFOValuation();
        }
    }

}