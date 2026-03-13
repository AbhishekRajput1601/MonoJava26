package com.abhi.abstactinterfaceexception.asg3;

public class InternationalOrder extends Order {

    public InternationalOrder(String orderId, String customerName, double orderAmount) throws InvalidOrderException {
        super(orderId, customerName, orderAmount);
        System.out.println("InternationalOrder constructor executed");
    }

    double processOrder() {
        return orderAmount + 2000;
    }

    public boolean verifyOrder() {
        return orderAmount >= 1000;
    }
}