package model.productmodel;

public class NonPerishableProduct extends Product {

    public NonPerishableProduct(String name, int quantity, int reorderLevel, double price) {
        super(name, quantity, reorderLevel, price);
    }

    public boolean isPerishable() {
        return false;
    }
}
