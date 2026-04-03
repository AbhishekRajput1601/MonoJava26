package model.productmodel;

import java.time.LocalDate;

public class PerishableProduct extends Product {

    private final LocalDate expiryDate;

    public PerishableProduct(String name, int quantity, int reorderLevel, double price, LocalDate expiryDate) {
        super(name, quantity, reorderLevel, price);
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isPerishable() {
        return true;
    }


    @Override
    public String toString() {
        return super.toString() + " | Expiry: " + expiryDate;
    }
}
