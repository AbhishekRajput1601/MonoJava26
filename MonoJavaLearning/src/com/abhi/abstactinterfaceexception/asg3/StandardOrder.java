package com.abhi.abstactinterfaceexception.asg3;

public class StandardOrder extends Order {

    public StandardOrder(String orderId, String customerName, double orderAmount) throws InvalidOrderException {
        super(orderId, customerName, orderAmount);
        System.out.println("StandardOrder constructor executed");
    }

    double processOrder() {
        return orderAmount;
    }

    public boolean verifyOrder() {
        return orderAmount <= 10000;
    }
}
