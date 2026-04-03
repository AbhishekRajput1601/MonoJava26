package solid.assignment.asg1.test;

import solid.assignment.asg1.model.NotificationModel.EmailNotifier;
import solid.assignment.asg1.model.NotificationModel.Notifier;
import solid.assignment.asg1.model.NotificationModel.SMSNotifier;
import solid.assignment.asg1.model.ValuationModel.FIFOValuation;
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

        ValuationStrategy valuationStrategy = new FIFOValuation();

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
                    updateProduct(inventoryService);
                    break;
                case 4:
                    deleteProduct(inventoryService);
                    break;
                case 5:
                    inventoryService.calculateInventoryValue(); // MOVED
                    break;
                case 6:
                    inventoryService.showAllProducts(); // MOVED
                    break;
                case 7:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Inventory System =====");
        System.out.println("1. Add Product");
        System.out.println("2. Remove Stock");
        System.out.println("3. Update Product");
        System.out.println("4. Delete Product");
        System.out.println("5. Calculate Value");
        System.out.println("6. Show Products");
        System.out.println("7. Exit");
    }

    private static void addProduct(InventoryService service) {
        String name = getValidString("Enter product name: ");

        int qty = getValidInt("Enter quantity: ");
        int reorderLevel = getValidInt("Enter reorder level: ");
        double price = getValidDouble("Enter price: ");

        System.out.print("Is perishable? (yes/no): ");
        boolean perishable = scanner.nextLine().equalsIgnoreCase("yes");

        Product product = new Product(name, qty, reorderLevel, price, perishable);
        service.addProduct(product);

        System.out.println("Product added successfully.");
    }

    private static void removeStock(InventoryService service) {
        while (true) {
            String name = getValidString("Enter product name: ");

            if (!service.productExists(name)) {
                System.out.println("Product not found. Please re-enter.");
                continue;
            }

            int qty = getValidInt("Enter quantity: ");
            service.removeStock(name, qty);
            break;
        }
    }

    private static void updateProduct(InventoryService service) {
        while (true) {
            String name = getValidString("Enter product name to update: ");

            if (!service.productExists(name)) {
                System.out.println("Product not found. Please re-enter.");
                continue;
            }

            int qty = getValidInt("Enter new quantity: ");
            int reorderLevel = getValidInt("Enter new reorder level: ");
            double price = getValidDouble("Enter new price: ");

            service.updateProduct(name, qty, reorderLevel, price);
            break;
        }
    }

    private static void deleteProduct(InventoryService service) {
        while (true) {
            String name = getValidString("Enter product name to delete: ");

            if (!service.productExists(name)) {
                System.out.println("Product not found. Please re-enter.");
                continue;
            }

            service.deleteProduct(name);
            break;
        }
    }

    private static String getValidString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (input.trim().isEmpty()) {
                System.out.println("Input cannot be empty.");
            } else {
                return input;
            }
        }
    }

    private static int getValidInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine());

                if (value < 0) {
                    System.out.println("Cannot be negative.");
                    continue;
                }
                return value;
            } catch (Exception e) {
                System.out.println("Invalid number.");
            }
        }
    }

    private static double getValidDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine());

                if (value < 0) {
                    System.out.println("Cannot be negative.");
                    continue;
                }
                return value;
            } catch (Exception e) {
                System.out.println("Invalid number.");
            }
        }
    }
}