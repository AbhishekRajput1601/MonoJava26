package com.abhi.comparableandcomparator.asg2;

import java.util.*;

public class FlightMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Flight> list = new ArrayList<>();

        System.out.print("Enter number of flights: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.print("Enter airline: ");
            String airline = scanner.nextLine();

            double fare;
            while (true) {

                System.out.print("Enter fare: ");

                if (scanner.hasNextDouble()) {
                    fare = scanner.nextDouble();
                    if (fare >= 0) break;
                }

                System.out.println("Invalid fare!");
                scanner.nextLine();
            }

            scanner.nextLine();
            list.add(new Flight(airline, fare));
        }

        list.sort((f1, f2) -> Double.compare(f2.fare, f1.fare));

        System.out.println("\nSorted Flights:");
        for (Flight f : list)
            System.out.println(f);
    }
}
