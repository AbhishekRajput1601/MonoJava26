package com.abhi.arrayofobjectsassignment.asg3;
import java.util.Scanner;

public class TollManagementApplication {

    private static int vehicleIdCounter = 1000;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Vehicle[] vehicleArray = new Vehicle[100];
        int index = 0;

        while (true) {

            System.out.println("\n===== Toll Management System =====");
            System.out.println("1. Process Car");
            System.out.println("2. Process Truck");
            System.out.println("3. Process Motorcycle");
            System.out.println("4. Display Summary");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter number only.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    String carNumber = readValidVehicleNumber(scanner);
                    double carToll = readPositiveDouble(scanner, "Enter Base Toll Amount: ");

                    vehicleArray[index++] =
                            new Car(vehicleIdCounter++, carNumber, carToll);

                    double carFinalToll =
                            vehicleArray[index - 1].calculateToll(0);

                    vehicleArray[index - 1].updateTotalToll(carFinalToll);

                    System.out.println("Car Toll: " + carFinalToll);
                    break;

                case 2:
                    String truckNumber = readValidVehicleNumber(scanner);
                    double truckBase = readPositiveDouble(scanner, "Enter Base Toll Amount: ");
                    double loadCharge = readPositiveDouble(scanner, "Enter Load Charge: ");

                    vehicleArray[index++] =
                            new Truck(vehicleIdCounter++, truckNumber,
                                    truckBase, loadCharge);

                    double truckFinalToll =
                            vehicleArray[index - 1].calculateToll(0);

                    vehicleArray[index - 1].updateTotalToll(truckFinalToll);

                    System.out.println("Truck Toll: " + truckFinalToll);
                    break;

                case 3:
                    String motorcycleNumber = readValidVehicleNumber(scanner);
                    double motorcycleToll = readPositiveDouble(scanner, "Enter Base Toll Amount: ");

                    vehicleArray[index++] =
                            new Motorcycle(vehicleIdCounter++, motorcycleNumber, motorcycleToll);

                    double motorcycleFinalToll =
                            vehicleArray[index - 1].calculateToll(0);

                    vehicleArray[index - 1].updateTotalToll(motorcycleFinalToll);

                    System.out.println("Motorcycle Toll: " + motorcycleFinalToll);
                    break;

                case 4:
                    System.out.println("\n===== Toll Summary =====");
                    System.out.println("Total Vehicles Processed: "
                            + Vehicle.getTotalVehiclesProcessed());
                    System.out.println("Total Toll Collected: "
                            + Vehicle.getTotalTollCollected());
                    break;

                case 5:
                    System.out.println("Exiting Toll System...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }


    private static String readValidVehicleNumber(Scanner scanner) {

        while (true) {

            System.out.print("Enter Vehicle Number (e.g., MH12AB1234): ");
            String number = scanner.nextLine().trim().toUpperCase();

            // Indian Standard Vehicle Number Regex
            String regex = "^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$";

            if (number.matches(regex)) {
                return number;
            } else {
                System.out.println("Invalid Indian Vehicle Number Format!");
                System.out.println("Format must be like: MH12AB1234");
                System.out.println("Please enter again.\n");
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

            if (value > 0) {
                return value;
            } else {
                System.out.println("Value must be positive.");
            }
        }
    }
}