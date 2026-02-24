package com.abhi.encapsulation.constructorAssignment.asg3;

public class Product {

    private static int idCounter = 100;

    private final int productId;
    private double price;
    private int stockQuantity;

    // Constructor (price mandatory)
    public Product(double price, int stockQuantity) {

        this.productId = ++idCounter;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
}
