package com.abhi.comparatorassignment.asg2;

import java.util.*;

public class OrderManager {

    private Queue<Order> dispatchQueue = new LinkedList<>();
    private Set<String> processedOrderIds = new HashSet<>();
    private List<Order> allOrders = new ArrayList<>();

    public void addOrder(Order order) {
        if (!processedOrderIds.contains(order.getOrderId())) {
            dispatchQueue.add(order);
            allOrders.add(order);
            processedOrderIds.add(order.getOrderId());
        } else {
            System.out.println("Duplicate Order ID");
        }
    }

    public void processNext() {
        if (dispatchQueue.isEmpty()) {
            System.out.println("No orders to process");
            return;
        }
        Order order = dispatchQueue.poll();
        order.process();
    }

    public void displayAll() {
        if(allOrders.isEmpty()){
            System.out.println("No orders to show");
        }
        for (Order o : allOrders) {
            System.out.println(o.getOrderId() + " " + o.getCustomerName() + " " + o.getTotalAmount());
        }
    }

    public void sortByAmount() {
        allOrders.sort(new AmountComparator());
        displayAll();
    }
    public void sortByDate() {
        allOrders.sort(new DateComparator());
        displayAll();
    }
}
