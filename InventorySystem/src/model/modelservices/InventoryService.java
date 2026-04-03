package model.modelservices;


import model.notificationmodel.Notifier;
import model.productmodel.Product;
import model.validationmodel.InvalidInputException;
import model.valuationmodel.ValuationStrategy;

import java.util.*;

public class InventoryService {

    private final List<Product> products = new ArrayList<>();
    private final List<Notifier> notifiers;
    private final ReorderService reorderService;
    private final ProductService productService;
    private final Map<Boolean, ValuationStrategy> strategyMap;

    public InventoryService(List<Notifier> notifiers,
                            ReorderService reorderService,
                            ProductService productService,
                            Map<Boolean, ValuationStrategy> strategyMap) {
        this.notifiers = notifiers;
        this.reorderService = reorderService;
        this.productService = productService;
        this.strategyMap = strategyMap;
    }

    private Product getExistingProduct(String name) {
        return productService.getExistingProduct(products, name);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addStock(String name, int qty) {
        Product p = getExistingProduct(name);
        p.addStock(qty);
    }

    public void removeStock(String name, int quantity) {
        Product product = getExistingProduct(name);
        if (quantity > product.getQuantity()) {
            throw new InvalidInputException("Not enough stock for product: " + name);
        }

        product.removeStock(quantity);
        System.out.println("Stock removed successfully.");
        checkReorder(product);
    }

    public void updateQuantity(String name, int qty) {
        Product p = getExistingProduct(name);
        p.updateProduct(qty, p.getReorderLevel(), p.getPrice());
    }

    public void updateReorderLevel(String name, int reorder) {
        Product p = getExistingProduct(name);
        p.updateProduct(p.getQuantity(), reorder, p.getPrice());
    }

    public void updatePrice(String name, double price) {
        Product p = getExistingProduct(name);
        p.updateProduct(p.getQuantity(), p.getReorderLevel(), price);
    }

    public void deleteProduct(String name) {
        Product p = getExistingProduct(name);
        products.remove(p);
    }

    private void checkReorder(Product product) {
        if (product.getQuantity() <= product.getReorderLevel()) {
            System.out.println("Reorder level reached for product: " + product.getName());
            reorderService.reorder(product);
            notifiers.forEach(notifier ->
                    notifier.notify("Low Quantity in stock of : " + product.getName())
            );
        }
    }

    public void calculateInventoryValue() {

        List<Product> perishable = new ArrayList<>();
        List<Product> nonPerishable = new ArrayList<>();

        for (Product p : products) {
            if (p.isPerishable()) perishable.add(p);
            else nonPerishable.add(p);
        }

        double pVal = strategyMap.get(true).calculateValue(perishable);
        double npVal = strategyMap.get(false).calculateValue(nonPerishable);

        System.out.println("Perishable Product Value: " + pVal);
        System.out.println("Non-Perishable Product Value: " + npVal);
        System.out.println("Total Value: " + (pVal + npVal));
    }

    public void showAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products");
            return;
        }
        System.out.println("\n===== All Products =====");
        products.forEach(System.out::println);
    }

    public int getProductQuantity(String name) {
        return getExistingProduct(name).getQuantity();
    }
}