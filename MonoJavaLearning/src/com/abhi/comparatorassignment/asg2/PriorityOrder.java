package com.abhi.comparatorassignment.asg2;

import java.util.Date;

public class PriorityOrder extends Order {

    private int priorityLevel;
    private double expressFee;

    public PriorityOrder(String orderId, String customerName, double totalAmount, Date orderDate, int priorityLevel, double expressFee) {
        super(orderId, customerName, totalAmount, orderDate);
        this.priorityLevel = priorityLevel;
        this.expressFee = expressFee;
    }

    @Override
    public void process() {
        System.out.println("Priority Order processed with level " + priorityLevel + "and expressFee" + expressFee);
    }
}
