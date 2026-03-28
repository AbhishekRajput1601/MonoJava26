package com.abhi.comparatorassignment.asg5;

import java.util.Comparator;

public class RouteComparator implements Comparator<Passenger> {

    @Override
    public int compare(Passenger p1, Passenger p2) {
        return p1.routeNumber - p2.routeNumber;
    }
}