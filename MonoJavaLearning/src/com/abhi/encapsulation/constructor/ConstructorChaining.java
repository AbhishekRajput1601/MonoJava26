package com.abhi.encapsulation.constructor;

import java.util.Scanner;

public class ConstructorChaining {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Car car;

        while (true) {

            System.out.println("\nSelect Constructor:");
            System.out.println("1. Default Constructor");
            System.out.println("2. Model & Description Constructor");
            System.out.println("3. Model, Description & Year Constructor");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.out.println("Invalid input. Please enter number between 1 and 4.");
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    car = new Car();
                    System.out.println("\nCar Details:");
                    car.getCarDetails();
                    break;

                case 2:
                    String model = readNonEmpty(sc, "Enter Model Name: ");
                    String des = readNonEmpty(sc, "Enter Description: ");
                    car = new Car(model, des);
                    System.out.println("\nCar Details:");
                    car.getCarDetails();
                    break;

                case 3:
                    String model3 = readNonEmpty(sc, "Enter Model Name: ");
                    String des3 = readNonEmpty(sc, "Enter Description: ");
                    int year = readValidYear(sc);
                    car = new Car(model3, des3, year);
                    System.out.println("\nCar Details:");
                    car.getCarDetails();
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter 1 to 4.");
            }
        }
    }

    private static String readNonEmpty(Scanner sc, String message) {
        String input;
        while (true) {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Try again.");
        }
    }

    private static int readValidYear(Scanner sc) {
        int year;
        while (true) {
            System.out.print("Enter Year: ");
            if (sc.hasNextInt()) {
                year = sc.nextInt();
                sc.nextLine();
                if (year >= 1800 && year <= 2026) {
                    return year;
                }
            } else {
                sc.nextLine();
            }
            System.out.println("Invalid year. Enter a valid year between 1800 and 2026.");
        }
    }
}
