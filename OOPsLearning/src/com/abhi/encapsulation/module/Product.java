package com.abhi.encapsulation.module;

public class Product {

    private static int productCounter = 1000;

    private final int productId;

    private String productName;
    private double price;
    private int stockQuantity;
    private boolean discontinued;

    public Product(String productName, double price, int initialStock) {
        this.productId = ++productCounter;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = initialStock;
        this.discontinued = false;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void updatePrice(double newPrice, boolean isAdmin) {

        if (discontinued) {
            System.out.println("Discontinued product cannot be modified.");
            return;
        }

        if (!isAdmin) {
            System.out.println("Only admin can update price.");
            return;
        }

        if (newPrice < 0) {
            System.out.println("Price cannot be negative.");
            return;
        }

        this.price = newPrice;
        System.out.println("Price updated successfully!");
    }

    public void increaseStock(int quantity) {

        if (discontinued) {
            System.out.println("Discontinued product cannot be modified.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Increase amount must be positive.");
            return;
        }

        stockQuantity += quantity;
        System.out.println("Stock increased successfully!");
    }

    public void reduceStock(int quantity) {

        if (discontinued) {
            System.out.println("Discontinued product cannot be modified.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Reduction amount must be positive.");
            return;
        }

        if (quantity > stockQuantity) {
            System.out.println("Insufficient stock.");
            return;
        }

        stockQuantity -= quantity;
        System.out.println("Order processed successfully!");
    }

    public void discontinueProduct() {
        discontinued = true;
        System.out.println("Product discontinued successfully!");
    }
}
