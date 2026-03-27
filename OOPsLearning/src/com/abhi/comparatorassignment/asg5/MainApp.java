package com.abhi.comparatorassignment.asg5;

import java.util.Scanner;

public class MainApp {

    static Scanner scanner = new Scanner(System.in);
    static TransportManager manager = new TransportManager();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== TRANSPORT SYSTEM =====");
            System.out.println("1. Add Daily Passenger");
            System.out.println("2. Add Tourist Passenger");
            System.out.println("3. Process Next Passenger");
            System.out.println("4. Display All Passengers");
            System.out.println("5. Display Sorted by ID");
            System.out.println("6. Display Sorted by Name");
            System.out.println("7. Display Sorted by Route");
            System.out.println("8. Display By Route");
            System.out.println("9. Remove Passengers by Route");
            System.out.println("10. Exit");

            choice = getIntInput();

            switch (choice) {

                case 1:
                    addDailyPassenger();
                    break;

                case 2:
                    addTouristPassenger();
                    break;

                case 3:
                    manager.processNextPassenger();
                    break;

                case 4:
                    manager.displayAllPassengers();
                    break;

                case 5:
                    manager.displaySortedById();
                    break;

                case 6:
                    manager.displaySortedByName();
                    break;

                case 7:
                    manager.displaySortedByRoute();
                    break;

                case 8:
                    System.out.print("Enter route number: ");
                    int route = getIntInput();
                    manager.displayByRoute(route);
                    break;

                case 9:
                    System.out.print("Enter route to remove: ");
                    int r = getIntInput();
                    manager.removePassengersByRoute(r);
                    break;

                case 10:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 10);
    }

    static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter valid number");
            scanner.next();
        }
        return scanner.nextInt();
    }

    static void addDailyPassenger() {

        System.out.print("Enter ID: ");
        String id = scanner.next();

        System.out.print("Enter Name: ");
        String name = scanner.next();

        System.out.print("Enter Route Number: ");
        int route = getIntInput();

        System.out.print("Enter Pass Type: ");
        String passType = scanner.next();

        Passenger p = new DailyPassenger(id, name, route, passType);
        manager.addPassenger(p);
    }

    static void addTouristPassenger() {

        System.out.print("Enter ID: ");
        String id = scanner.next();

        System.out.print("Enter Name: ");
        String name = scanner.next();

        System.out.print("Enter Route Number: ");
        int route = getIntInput();

        System.out.print("Enter Number of Days: ");
        int days = getIntInput();

        Passenger p = new TouristPassenger(id, name, route, days);
        manager.addPassenger(p);
    }
}
