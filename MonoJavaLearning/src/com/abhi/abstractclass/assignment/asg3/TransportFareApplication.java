package com.abhi.abstractclass.assignment.asg3;
import java.util.Scanner;

public class TransportFareApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int totalTransport = readPositiveInt(scanner, "Enter Total number of transport : ");
        Transport[] transportArray = new Transport[totalTransport];
        int index = 0;

        while (true) {
            System.out.println("\n===== Transport Fare Calculation Engine =====");
            System.out.println("1. Add Bus");
            System.out.println("2. Add Metro");
            System.out.println("3. Add Taxi");
            System.out.println("4. Calculate Fare for All");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter numeric value.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    if (checkLimit(index, transportArray.length)) break;
                    String busRoute = readValidRouteId(scanner);
                    double busBaseFare = readPositiveDouble(scanner, "Enter Base Fare: ");
                    double busDistance = readPositiveDouble(scanner, "Enter Distance (km): ");
                    transportArray[index++] = new Bus(busRoute, busBaseFare, busDistance);
                    System.out.println("Bus added successfully.");
                    break;

                case 2:
                    if (checkLimit(index, transportArray.length)) break;
                    String metroRoute = readValidRouteId(scanner);
                    double metroBaseFare = readPositiveDouble(scanner, "Enter Base Fare: ");
                    int numStations = readPositiveInt(scanner, "Enter Number of Stations: ");
                    transportArray[index++] = new Metro(metroRoute, metroBaseFare, numStations);
                    System.out.println("Metro added successfully.");
                    break;

                case 3:
                    if (checkLimit(index, transportArray.length)) break;
                    String taxiRoute = readValidRouteId(scanner);
                    double taxiBaseFare = readPositiveDouble(scanner, "Enter Base Fare: ");
                    double taxiDistance = readPositiveDouble(scanner, "Enter Distance (km): ");
                    double taxiTime = readPositiveDouble(scanner, "Enter Time (hours): ");
                    transportArray[index++] = new Taxi(taxiRoute, taxiBaseFare, taxiDistance, taxiTime);
                    System.out.println("Taxi added successfully.");
                    break;

                case 4:
                    if (index == 0) {
                        System.out.println("No transport data available.");
                    } else {
                        System.out.println("\nCalculating Fare for All Transport...");
                        for (int i = 0; i < index; i++) {
                            transportArray[i].calculateFare();
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting Transport Fare Calculation Engine...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Enter 1-5.");
            }
        }
    }

    private static boolean checkLimit(int index, int length) {
        if (index >= length) {
            System.out.println("Transport limit reached! Process existing transport first.");
            return true;
        }
        return false;
    }

    private static String readValidRouteId(Scanner scanner) {
        while (true) {
            System.out.print("Enter Route ID: ");
            String route = scanner.nextLine().trim();
            if (route.isEmpty() || !route.matches("^[A-Za-z0-9]+$")) {
                System.out.println("Invalid Route ID! Only letters and numbers allowed. Enter again.");
            } else {
                return route;
            }
        }
    }

    private static double readPositiveDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid input! Enter numeric value.");
                scanner.next();
                continue;
            }
            double value = scanner.nextDouble();
            scanner.nextLine();
            if (value <= 0) {
                System.out.println("Value must be greater than 0. Enter again.");
            } else return value;
        }
    }

    private static int readPositiveInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter numeric value.");
                scanner.next();
                continue;
            }
            int value = scanner.nextInt();
            scanner.nextLine();
            if (value <= 0) {
                System.out.println("Value must be greater than 0. Enter again.");
            } else return value;
        }
    }
}