package test;

import model.modelservices.InventoryService;
import model.productmodel.NonPerishableProduct;
import model.productmodel.PerishableProduct;
import model.productmodel.Product;
import model.validationmodel.InputValidator;

import java.util.Scanner;

public class InventoryMenu {

    private final InventoryService inventoryService;
    private final Scanner scanner = new Scanner(System.in);

    public InventoryMenu(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void start() {
        while (true) {
            System.out.println("\n===== Inventory System =====");
            System.out.println("1. Add Product Into Inventory");
            System.out.println("2. Update Product From Inventory");
            System.out.println("3. Delete Product From Inventory");
            System.out.println("4. Add Quantity Into Stock");
            System.out.println("5. Remove Quantity From Stock");
            System.out.println("6. Calculate Total Value");
            System.out.println("7. Show All Products");
            System.out.println("8. Exit");

            int userChoice = InputValidator.readChoice(scanner, "Enter your choice (1-8): ", 1, 8);

            try {
                switch (userChoice) {
                    case 1 -> addProduct();
                    case 2 -> updateProduct();
                    case 3 -> deleteProduct();
                    case 4 -> addStockToProduct();
                    case 5 -> removeStockFromProduct();
                    case 6 -> inventoryService.calculateInventoryValue();
                    case 7 -> inventoryService.showAllProducts();
                    case 8 -> System.exit(0);
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void addProduct() {
        String productName = InputValidator.readString(scanner, "Enter Product Name: ");
        int productQuantity = InputValidator.readInt(scanner, "Enter Product Quantity: ");
        int reorderLevel = InputValidator.readReorderLevel(
                scanner,
                "Enter Reorder Level: ",
                productQuantity
        );
        double productPrice = InputValidator.readDouble(scanner, "Enter Product Price: ");
        boolean isPerishable = InputValidator.readYesNo(scanner, "Is Product Perishable (yes/no): ");

        Product product;

        if (isPerishable) {
            var expiryDate = InputValidator.readDate(scanner, "Enter Expiry Date");
            product = new PerishableProduct(productName, productQuantity, reorderLevel, productPrice, expiryDate);
        } else {
            product = new NonPerishableProduct(productName, productQuantity, reorderLevel, productPrice);
        }

        inventoryService.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private void updateProduct() {

        String productName = InputValidator.readExistingProductName(
                scanner,
                "Enter Product Name to Update: ",
                inventoryService
        );

        System.out.println("Select field to update:");
        System.out.println("1. Update Quantity");
        System.out.println("2. Update Reorder Level");
        System.out.println("3. Update Price");

        int updateChoice = InputValidator.readChoice(scanner, "Enter your choice (1-3): ", 1, 3);

        if (updateChoice == 1) {
            int newQuantity = InputValidator.readInt(scanner, "Enter New Quantity: ");
            inventoryService.updateQuantity(productName, newQuantity);
            System.out.println("Product quantity updated successfully.");
        }
        else if (updateChoice == 2) {
            int currentQuantity = inventoryService.getProductQuantity(productName);

            int newReorderLevel = InputValidator.readReorderLevel(
                    scanner,
                    "Enter New Reorder Level: ",
                    currentQuantity
            );

            inventoryService.updateReorderLevel(productName, newReorderLevel);
            System.out.println("Product reorder level updated successfully.");
        }
        else {
            double newPrice = InputValidator.readDouble(scanner, "Enter New Price: ");
            inventoryService.updatePrice(productName, newPrice);
            System.out.println("Product price updated successfully.");
        }
    }

    private void deleteProduct() {
        String productName = InputValidator.readExistingProductName(
                scanner,
                "Enter Product Name to Delete: ",
                inventoryService
        );

        inventoryService.deleteProduct(productName);
        System.out.println("Product deleted successfully.");
    }

    private void addStockToProduct() {
        String productName = InputValidator.readExistingProductName(
                scanner,
                "Enter Product Name to Add Stock: ",
                inventoryService
        );

        int quantityToAdd = InputValidator.readInt(scanner, "Enter Quantity to Add: ");
        inventoryService.addStock(productName, quantityToAdd);
        System.out.println("Stock added successfully.");
    }

    private void removeStockFromProduct() {
        String productName = InputValidator.readExistingProductName(
                scanner,
                "Enter Product Name to Remove Stock: ",
                inventoryService
        );

        int quantityToRemove = InputValidator.readInt(scanner, "Enter Quantity to Remove: ");
        inventoryService.removeStock(productName, quantityToRemove);
    }
}