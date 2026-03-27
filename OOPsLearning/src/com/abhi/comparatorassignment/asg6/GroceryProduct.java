package com.abhi.comparatorassignment.asg6;


public class GroceryProduct extends Product {

    private int expiryDays;

    public GroceryProduct(String productId, String name, String category, double price, int expiryDays) {
        super(productId, name, category, price);
        this.expiryDays = expiryDays;
    }

    @Override
    public void processReturn() {
        System.out.println("Grocery product returned: " + productId);
    }
}
