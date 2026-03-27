package com.abhi.comparatorassignment.asg2;

import java.util.Date;

public class RegularOrder extends Order {

    private int shippingDays;

    public RegularOrder(String orderId, String customerName, double totalAmount, Date orderDate, int shippingDays) {
        super(orderId, customerName, totalAmount, orderDate);
        this.shippingDays = shippingDays;
    }

    @Override
    public void process() {
        System.out.println("Regular Order processed in " + shippingDays + " days");
    }
}