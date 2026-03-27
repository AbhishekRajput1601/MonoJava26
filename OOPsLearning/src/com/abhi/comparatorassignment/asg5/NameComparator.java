package com.abhi.comparatorassignment.asg5;

import java.util.Comparator;

public class NameComparator implements Comparator<Passenger> {

    @Override
    public int compare(Passenger p1, Passenger p2) {
        return p1.name.compareTo(p2.name);
    }
}