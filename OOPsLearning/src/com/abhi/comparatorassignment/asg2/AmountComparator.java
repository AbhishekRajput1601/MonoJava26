package com.abhi.comparatorassignment.asg2;

import java.util.Comparator;

public class AmountComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        return Double.compare(o1.getTotalAmount(), o2.getTotalAmount());
    }
}
