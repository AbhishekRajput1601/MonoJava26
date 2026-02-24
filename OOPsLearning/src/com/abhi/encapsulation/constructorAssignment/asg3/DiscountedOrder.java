package com.abhi.encapsulation.constructorAssignment.asg3;

public class DiscountedOrder extends Order {

    private double discountPercent;

    public DiscountedOrder(Product product, int quantity, double discountPercent) {

        super(product, quantity);

        this.discountPercent = discountPercent;

        double discountAmount = totalAmount * (discountPercent / 100);
        this.totalAmount = totalAmount - discountAmount;
    }
}