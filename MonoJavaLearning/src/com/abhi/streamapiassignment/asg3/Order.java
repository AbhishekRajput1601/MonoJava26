package com.abhi.streamapiassignment.asg3;

import java.io.Serializable;

public class Order implements Serializable {
    private int orderId;
    private String customerName;
    private String category;
    private double amount;
    private String status;

    public Order(int orderId, String customerName, String category, double amount, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.category = category;
        this.amount = amount;
        this.status = status;
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "OrderID: " + orderId + ", Customer: " + customerName +
                ", Category: " + category + ", Amount: " + amount +
                ", Status: " + status;
    }
}
