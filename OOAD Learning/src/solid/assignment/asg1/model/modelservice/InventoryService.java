package solid.assignment.asg1.model.modelservice;

import solid.assignment.asg1.model.NotificationModel.Notifier;
import solid.assignment.asg1.model.ValuationModel.ValuationStrategy;

import java.util.*;

public class InventoryService {
    private final List<Product> products = new ArrayList<>();
    private final List<Notifier> notifiers;
    private final ReorderService reorderService;
    private final ValuationStrategy valuationStrategy;

    public InventoryService(List<Notifier> notifiers,
                            ReorderService reorderService,
                            ValuationStrategy valuationStrategy) {
        this.notifiers = notifiers;
        this.reorderService = reorderService;
        this.valuationStrategy = valuationStrategy;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeStock(String productName, int qty) {
        for (Product p : products) {
            if (p.getName().equals(productName)) {
                p.removeStock(qty);
                System.out.println("Stock updated: Removed " + qty + " units of '" + productName + "'");
                System.out.println("Current stock for " + productName + ": " + p.getQuantity());

                checkReorder(p);
                return;
            }
        }
    }

    private void checkReorder(Product product) {
        if (product.getQuantity() <= product.getReorderLevel()) {
            System.out.println("Reorder threshold reached for '" + product.getName() + "'. Triggering reorder...");

            reorderService.reorder(product);

            for (Notifier notifier : notifiers) {
                notifier.notify("Low stock alert for '" + product.getName() + "'");
            }
        }
    }

    public void calculateInventoryValue() {
        double value = valuationStrategy.calculateValue(products);
        System.out.println("Total inventory value: $" + value);
    }

    public void showAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\nName\tQuantity\tReorderLevel\tPrice");

        for (Product p : products) {
            System.out.println(
                    p.getName() + "\t" + p.getQuantity() + "\t\t" + p.getReorderLevel() + "\t\t" + p.getPrice()
            );
        }
    }
}
