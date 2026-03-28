package com.abhi.comparatorassignment.asg5;

import java.util.*;

public class TransportManager {

    private Queue<Passenger> waitingQueue = new LinkedList<>();
    private Set<String> passengerIds = new HashSet<>();
    private Map<Integer, List<Passenger>> routeMap = new HashMap<>();
    private List<Passenger> allPassengers = new ArrayList<>();

    public void addPassenger(Passenger passenger) {

        if (passengerIds.contains(passenger.getPassengerId())) {
            System.out.println("Duplicate passenger not allowed");
            return;
        }

        waitingQueue.add(passenger);
        passengerIds.add(passenger.getPassengerId());
        allPassengers.add(passenger);

        routeMap.putIfAbsent(passenger.getRouteNumber(), new ArrayList<>());
        routeMap.get(passenger.getRouteNumber()).add(passenger);

        System.out.println("Passenger added");
    }

    public void processNextPassenger() {

        if (waitingQueue.isEmpty()) {
            System.out.println("No passengers waiting");
            return;
        }

        Passenger passenger = waitingQueue.poll();
        passenger.travel();
    }

    public void displayAllPassengers() {

        for (Passenger p : allPassengers) {
            System.out.println(p.getPassengerId() + " " + p.name + " Route: " + p.routeNumber);
        }
    }

    public void displaySortedById() {

        List<Passenger> list = new ArrayList<>(allPassengers);
        Collections.sort(list);

        for (Passenger p : list) {
            System.out.println(p.getPassengerId());
        }
    }

    public void displaySortedByName() {

        List<Passenger> list = new ArrayList<>(allPassengers);
        list.sort(new NameComparator());

        for (Passenger p : list) {
            System.out.println(p.name);
        }
    }

    public void displaySortedByRoute() {

        List<Passenger> list = new ArrayList<>(allPassengers);
        list.sort(new RouteComparator());

        for (Passenger p : list) {
            System.out.println(p.getPassengerId() + " Route: " + p.routeNumber);
        }
    }

    public void displayByRoute(int route) {

        List<Passenger> list = routeMap.get(route);

        if (list == null) {
            System.out.println("No passengers found");
            return;
        }

        for (Passenger p : list) {
            System.out.println(p.getPassengerId());
        }
    }

    public void removePassengersByRoute(int route) {

        Iterator<Passenger> iterator = allPassengers.iterator();

        while (iterator.hasNext()) {
            Passenger p = iterator.next();

            if (p.getRouteNumber() == route) {
                iterator.remove();
                passengerIds.remove(p.getPassengerId());
                waitingQueue.remove(p);
            }
        }
    }
}
