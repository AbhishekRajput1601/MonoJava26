package com.abhi.comparatorassignment.asg6;


public class ElectronicProduct extends Product {

    private int warrantyMonths;

    public ElectronicProduct(String productId, String name, String category, double price, int warrantyMonths) {
        super(productId, name, category, price);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public void processReturn() {
        System.out.println("Electronic product returned: " + productId);
    }
}