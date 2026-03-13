package com.abhi.abstactinterfaceexception.asg3;

public class ExpressOrder extends Order {

    public ExpressOrder(String orderId, String customerName, double orderAmount) throws InvalidOrderException {
        super(orderId, customerName, orderAmount);
        System.out.println("ExpressOrder constructor executed");
    }

    double processOrder() {
        return orderAmount + 500;
    }

    public boolean verifyOrder() {
        return orderAmount <= 20000;
    }
}