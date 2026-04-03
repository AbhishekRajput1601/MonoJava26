package solid.assignment.asg1.model.modelservice;

import solid.assignment.asg1.model.NotificationModel.Notifier;
import solid.assignment.asg1.model.ValuationModel.FIFOValuation;
import solid.assignment.asg1.model.ValuationModel.LIFOValuation;
import solid.assignment.asg1.model.ValuationModel.ValuationStrategy;

import java.util.*;

public class InventoryService {
    private final List<Product> products = new ArrayList<>();
    private final List<Notifier> notifiers;
    private final ReorderService reorderService;

    public InventoryService(List<Notifier> notifiers,
                            ReorderService reorderService,
                            ValuationStrategy valuationStrategy) {
        this.notifiers = notifiers;
        this.reorderService = reorderService;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeStock(String productName, int qty) {
        Product p = findProduct(productName);

        if (p == null) {
            System.out.println("Product not found.");
            return;
        }

        p.removeStock(qty);

        System.out.println("Stock updated: Removed " + qty + " units of '" + productName + "'");
        System.out.println("Current stock for " + productName + ": " + p.getQuantity());

        checkReorder(p);
    }


    public void deleteProduct(String productName) {
        Product p = findProduct(productName);

        if (p != null) {
            products.remove(p);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }


    public void updateProduct(String productName, int qty, int reorderLevel, double price) {
        Product p = findProduct(productName);

        if (p != null) {
            p.updateProduct(qty, reorderLevel, price);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private Product findProduct(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    private void checkReorder(Product product) {
        if (product.getQuantity() <= product.getReorderLevel()) {
            System.out.println("Reorder threshold reached for '" + product.getName() + "'.");

            reorderService.reorder(product);

            for (Notifier notifier : notifiers) {
                notifier.notify("Low stock alert for '" + product.getName() + "'");
            }
        }
    }


    public void calculateInventoryValue() {
        double total = 0;

        ValuationStrategy fifo = new FIFOValuation();
        ValuationStrategy lifo = new LIFOValuation();

        List<Product> perishable = new ArrayList<>();
        List<Product> nonPerishable = new ArrayList<>();

        for (Product p : products) {
            if (p.isPerishable()) {
                perishable.add(p);
            } else {
                nonPerishable.add(p);
            }
        }

        double fifoValue = fifo.calculateValue(perishable);
        double lifoValue = lifo.calculateValue(nonPerishable);

        total = fifoValue + lifoValue;

        System.out.println("\n----- Inventory Valuation -----");
        System.out.println("Perishable Products (FIFO): $" + fifoValue);
        System.out.println("Non-Perishable Products (LIFO): $" + lifoValue);
        System.out.println("--------------------------------");
        System.out.println("Total inventory value: $" + total);
    }

    public void showAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\n-------------------------------------------------------------");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",
                "Name", "Qty", "Reorder", "Price", "Perishable");
        System.out.println("-------------------------------------------------------------");

        for (Product p : products) {
            System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",
                    p.getName(),
                    p.getQuantity(),
                    p.getReorderLevel(),
                    p.getPrice(),
                    p.isPerishable());
        }

        System.out.println("-------------------------------------------------------------");
    }

    public boolean productExists(String name) {
        return findProduct(name) != null;
    }
}