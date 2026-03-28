package com.abhi.constructorandinheritance.asg4;

class Clothing extends Product {

    private String size;
    private String fabricType;

    public Clothing() {
        this(0, "Unknown", 0, "Unknown", "Unknown");
    }

    public Clothing(int productId, String productName, double basePrice, String size, String fabricType) {
        super(productId, productName, basePrice);
        this.size = size;
        this.fabricType = fabricType;
    }

    public void showSizeGuide() {
        System.out.println("Size Guide : " + size);
    }

    public void displayDetails() {
        System.out.println("\nClothing Product");
        super.displayDetails();
        System.out.println("Size       : " + size);
        System.out.println("FabricType : " + fabricType);
    }
}