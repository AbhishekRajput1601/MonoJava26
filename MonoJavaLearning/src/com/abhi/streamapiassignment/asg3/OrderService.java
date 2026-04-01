package com.abhi.streamapiassignment.asg3;
import java.util.*;
import java.util.stream.*;

public class OrderService {

    // 1. completed orders
    public static void getCompletedOrders(List<Order> orders) {
        orders.stream()
                .filter(o -> o.getStatus().equalsIgnoreCase("completed"))
                .forEach(System.out::println);
    }

    // 2. total revenue (completed orders)
    public static void getTotalRevenue(List<Order> orders) {
        double total = orders.stream()
                .filter(o -> o.getStatus().equalsIgnoreCase("completed"))
                .map(Order::getAmount)
                .reduce(0.0, Double::sum);

        System.out.println("Total Revenue: " + total);
    }

    // 3. group by category
    public static void groupByCategory(List<Order> orders) {
        Map<String, List<Order>> map = orders.stream()
                .collect(Collectors.groupingBy(Order::getCategory));

        map.forEach((k, v) -> {
            System.out.println("\nCategory: " + k);
            v.forEach(System.out::println);
        });
    }

    // 4. max order
    public static void getMaxOrder(List<Order> orders) {
        orders.stream()
                .max(Comparator.comparingDouble(Order::getAmount))
                .ifPresent(System.out::println);
    }

    // 5. count cancelled
    public static void countCancelled(List<Order> orders) {
        long count = orders.stream()
                .filter(o -> o.getStatus().equalsIgnoreCase("cancelled"))
                .count();

        System.out.println("Cancelled Orders: " + count);
    }

    // 6. orderId -> amount map
    public static void orderIdToAmountMap(List<Order> orders) {
        Map<Integer, Double> map = orders.stream()
                .collect(Collectors.toMap(Order::getOrderId, Order::getAmount));

        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    // 7. sorted customer names by amount desc
    public static void sortedCustomerNames(List<Order> orders) {
        orders.stream()
                .sorted(Comparator.comparingDouble(Order::getAmount).reversed())
                .map(Order::getCustomerName)
                .forEach(System.out::println);
    }
}
