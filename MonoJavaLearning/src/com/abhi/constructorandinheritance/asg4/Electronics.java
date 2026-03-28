package com.abhi.constructorandinheritance.asg4;

class Electronics extends Product {

    private int warrantyPeriod;
    private String brand;

    public Electronics() {
        this(0, "Unknown", 0, 0, "Unknown");
    }

    public Electronics(int productId, String productName, double basePrice, int warrantyPeriod, String brand) {
        super(productId, productName, basePrice);
        this.warrantyPeriod = warrantyPeriod;
        this.brand = brand;
    }

    public void checkWarranty() {
        System.out.println("Warranty Period : " + warrantyPeriod + " months");
    }

    public void displayDetails() {
        System.out.println("\nElectronics Product");
        super.displayDetails();
        System.out.println("Brand          : " + brand);
        System.out.println("WarrantyPeriod : " + warrantyPeriod + " months");
    }
}
