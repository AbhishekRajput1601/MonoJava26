package com.abhi.encapsulation.constructor;

import java.util.Scanner;

public class TestSuperChaining {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\nSelect Option:");
            System.out.println("1. Default Constructor");
            System.out.println("2. Parameterized Constructor");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.out.println("Invalid input. Enter number between 1 and 3.");
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    CarVehicle car1 = new CarVehicle();
                    break;

                case 2:
                    System.out.print("Enter Brand: ");
                    String brand = sc.nextLine();

                    System.out.print("Enter Model: ");
                    String model = sc.nextLine();

                    CarVehicle car2 = new CarVehicle(brand, model);
                    car2.getDetails();
                    break;

                case 3:
                    System.out.println("Exiting Program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Wrong choice! Please enter 1, 2 or 3.");
            }
        }
    }
}
