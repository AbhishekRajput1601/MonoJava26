package com.abhi.encapsulation.constructorAssignment.asg3;


public class Order {

    private static int orderCounter = 500;

    private final int orderId;
    protected Product product;
    protected int quantity;
    protected double totalAmount;

    public Order(Product product, int quantity) {

        this.orderId = ++orderCounter;
        this.product = product;
        this.quantity = quantity;

        this.totalAmount = product.getPrice() * quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
