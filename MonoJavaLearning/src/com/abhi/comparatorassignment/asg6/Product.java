package com.abhi.comparatorassignment.asg6;

public abstract class Product implements Comparable<Product> {

    protected String productId;
    protected String name;
    protected String category;
    protected double price;

    public Product(String productId, String name, String category, double price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getCategory() {
        return category;
    }

    public abstract void processReturn();

    @Override
    public int compareTo(Product other) {
        return this.productId.compareTo(other.productId);
    }
}
