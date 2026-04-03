package model.productmodel;

public abstract class Product {

    private String name;
    private int quantity;
    private int reorderLevel;
    private double price;

    public Product(String name, int quantity, int reorderLevel, double price) {
        this.name = name;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.price = price;
    }

    public abstract boolean isPerishable();

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public double getPrice() {
        return price;
    }

    public void addStock(int qty) {
        this.quantity += qty;
    }

    public void removeStock(int qty) {
        this.quantity -= qty;
    }

    public void updateProduct(int quantity, int reorderLevel, double price) {
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.price = price;
    }

    public String toString() {
        return name + " | Qty: " + quantity + " | Reorder: " + reorderLevel + " | Price: " + price;
    }
}