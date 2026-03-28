package com.abhi.abstactinterfaceexception.asg3;

public abstract class Order implements OrderVerification {

    protected String orderId;
    protected String customerName;
    protected double orderAmount;

    static {
        System.out.println("Order System Configuration Loaded");
    }

    public Order(String orderId, String customerName, double orderAmount) throws InvalidOrderException {

        if (orderAmount <= 0)
            throw new InvalidOrderException("Order amount must be positive");

        this.orderId = orderId;
        this.customerName = customerName;
        this.orderAmount = orderAmount;

        System.out.println("Order constructor executed");
    }

    abstract double processOrder();

    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Order Amount: " + orderAmount);
    }
}