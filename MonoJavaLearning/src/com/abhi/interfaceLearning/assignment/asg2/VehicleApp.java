package com.abhi.interfaceLearning.assignment.asg2;

import java.util.Scanner;

public class VehicleApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Vehicle Management System =====");
            System.out.println("1. Car");
            System.out.println("2. Bike");
            System.out.println("3. Exit");
            System.out.print("Select Vehicle: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter numeric value.");
                scanner.nextLine();
                continue;
            }

            int vehicleChoice = scanner.nextInt();
            scanner.nextLine();

            Vehicle vehicle = null;

            switch (vehicleChoice) {
                case 1:
                    vehicle = new Car();
                    break;
                case 2:
                    vehicle = new Bike();
                    break;
                case 3:
                    System.out.println("Exiting Vehicle Management System...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Choose 1 to 3.");
                    continue;
            }

            while (true) {
                System.out.println("\nSelect Action:");
                System.out.println("1. Start Vehicle");
                System.out.println("2. Stop Vehicle");
                System.out.println("3. Fuel Type");
                System.out.println("4. Back to Vehicle Selection");
                System.out.print("Enter your choice: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter numeric value.");
                    scanner.nextLine();
                    continue;
                }

                int actionChoice = scanner.nextInt();
                scanner.nextLine();

                switch (actionChoice) {
                    case 1:
                        System.out.println("Starting Vehicle...");
                        vehicle.start();
                        break;
                    case 2:
                        System.out.println("Stopping Vehicle...");
                        vehicle.stop();
                        break;
                    case 3:
                        System.out.println("Fuel Type: " + vehicle.fuelType());
                        break;
                    case 4:
                        System.out.println("Returning to Vehicle Selection...");
                        break;
                    default:
                        System.out.println("Invalid choice! Choose 1 to 4.");
                        continue;
                }

                if (actionChoice == 4) break;
            }
        }
    }
}