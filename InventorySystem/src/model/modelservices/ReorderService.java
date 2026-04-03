package model.modelservices;

import model.productmodel.Product;

public class ReorderService {
    private static final int qty = 25;
    public void reorder(Product product) {
        System.out.println("Reordered " + qty + " units of " + product.getName());
        product.addStock(qty);
    }
}