package com.abhi.constructorandinheritance.asg4;

class Product {

    private int productId;
    private String productName;
    private double basePrice;

    public Product() {
        this(0, "Unknown", 0);
    }

    public Product(int productId, String productName, double basePrice) {
        this.productId = productId;
        this.productName = productName;

        if (basePrice >= 0) {
            this.basePrice = basePrice;
        } else {
            this.basePrice = 0;
        }
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {

        if (basePrice >= 0) {
            this.basePrice = basePrice;
        }
    }

    public void displayDetails() {
        System.out.println("Product ID   : " + productId);
        System.out.println("Product Name : " + productName);
        System.out.println("Base Price   : " + basePrice);
    }
}
