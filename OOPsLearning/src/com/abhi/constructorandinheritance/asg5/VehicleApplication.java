package com.abhi.constructorandinheritance.asg5;

import java.util.Scanner;

public class VehicleApplication {

    private static Scanner scanner = new Scanner(System.in);

    public static int readInteger(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            }

            System.out.println("Invalid input");
        }
    }

    public static double readDouble(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (input.matches("\\d+(\\.\\d+)?")) {
                return Double.parseDouble(input);
            }

            System.out.println("Invalid input");
        }
    }

    public static String readString(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (input.matches("[A-Za-z]+( [A-Za-z]+)?")) {
                return input;
            }

            System.out.println("Invalid input");
        }
    }

    public static String readVehicleNumber(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().toUpperCase();

            if (input.matches("^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$")) {
                return input;
            }

            System.out.println("Invalid Indian vehicle number format");
        }
    }

    public static void main(String[] args) {

        VehicleSystem vehicleSystem = new VehicleSystem(10);

        while (true) {

            System.out.println("\n===== VEHICLE MENU =====");
            System.out.println("1. Register Car");
            System.out.println("2. Register Bike");
            System.out.println("3. Display Vehicles");
            System.out.println("4. Process Vehicles");
            System.out.println("5. Exit");

            int choice = readInteger("Enter choice: ");

            switch (choice) {

                case 1:

                    String carRegistration = readVehicleNumber("Enter Vehicle Number (e.g. MP04AB1234): ");
                    String carOwner = readString("Enter Owner Name: ");
                    double carCharge = readDouble("Enter Base Usage Charge: ");
                    int seats = readInteger("Enter Number Of Seats: ");
                    String fuelType = readString("Enter Fuel Type: ");

                    Car car = new Car(
                            carRegistration,
                            carOwner,
                            carCharge,
                            seats,
                            fuelType
                    );

                    vehicleSystem.addVehicle(car);
                    break;

                case 2:

                    String bikeRegistration = readVehicleNumber("Enter Vehicle Number (e.g. MP04AB1234): ");
                    String bikeOwner = readString("Enter Owner Name: ");
                    double bikeCharge = readDouble("Enter Base Usage Charge: ");
                    int engineCapacity = readInteger("Enter Engine Capacity: ");
                    String bikeType = readString("Enter Bike Type: ");

                    Bike bike = new Bike(
                            bikeRegistration,
                            bikeOwner,
                            bikeCharge,
                            engineCapacity,
                            bikeType
                    );

                    vehicleSystem.addVehicle(bike);
                    break;

                case 3:
                    vehicleSystem.displayAllVehicles();
                    break;

                case 4:
                    vehicleSystem.processVehicles();
                    break;

                case 5:
                    System.out.println("Exiting system");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
