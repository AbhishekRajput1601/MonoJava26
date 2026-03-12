package com.abhi.constructorandinheritance.asg4;

class InventorySystem {

    private Product[] products;
    private int totalProducts;

    public InventorySystem(int capacity) {
        products = new Product[capacity];
        totalProducts = 0;
    }

    public void addProduct(Product product) {

        if (totalProducts < products.length) {
            products[totalProducts] = product;
            totalProducts++;
        } else {
            System.out.println("Inventory full");
        }
    }

    public void displayInventory() {

        if (totalProducts == 0) {
            System.out.println("No products available");
            return;
        }

        for (int index = 0; index < totalProducts; index++) {
            products[index].displayDetails();
            System.out.println("----------------------------");
        }
    }

    public void processProducts() {

        for (int index = 0; index < totalProducts; index++) {

            if (products[index] instanceof Electronics) {
                ((Electronics) products[index]).checkWarranty();
            }

            if (products[index] instanceof Clothing) {
                ((Clothing) products[index]).showSizeGuide();
            }
        }
    }
}