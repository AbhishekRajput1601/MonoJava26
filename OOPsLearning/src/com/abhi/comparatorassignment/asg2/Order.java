package com.abhi.comparatorassignment.asg2;


import java.util.Date;

public abstract class Order implements Comparable<Order> {

    protected String orderId;
    protected String customerName;
    protected double totalAmount;
    protected Date orderDate;

    public Order(String orderId, String customerName, double totalAmount, Date orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public abstract void process();

    @Override
    public int compareTo(Order other) {
        return this.orderId.compareTo(other.orderId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Order)) return false;
        Order other = (Order) obj;
        return this.orderId.equals(other.orderId);
    }

    @Override
    public int hashCode() {
        return orderId.hashCode();
    }
}