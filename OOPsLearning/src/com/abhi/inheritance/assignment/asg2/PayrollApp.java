package com.abhi.inheritance.assignment.asg2;

import java.util.Scanner;

public class PayrollApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = readPositiveInt(scanner, "Enter number of employees: ");
        Employee[] employees = new Employee[size];

        for (int i = 0; i < size; i++) {

            System.out.println("\nSelect Employee Type");
            System.out.println("1. Full Time");
            System.out.println("2. Part Time");
            System.out.println("3. Contract Base");
            System.out.println("4. Exit");

            int choice = readMenuChoice(scanner);

            if (choice == 4) {
                System.out.println("Exiting program...");
                System.exit(0);
            }

            String name = readValidName(scanner, "Enter Employee Name: ");

            switch (choice) {

                case 1:
                    double basic = readPositiveDouble(scanner, "Enter Basic: ");
                    double hra = readPositiveDouble(scanner, "Enter HRA: ");
                    double da = readPositiveDouble(scanner, "Enter DA: ");
                    employees[i] = new FullTimeEmployee(name, basic, hra, da);
                    break;

                case 2:
                    double hours = readPositiveDouble(scanner, "Enter Hours Worked: ");
                    double rate = readPositiveDouble(scanner, "Enter Hourly Rate: ");
                    employees[i] = new PartTimeEmployee(name, hours, rate);
                    break;

                case 3:
                    double fixed = readPositiveDouble(scanner, "Enter Fixed Amount: ");
                    double tax = readPositiveDouble(scanner, "Enter Tax: ");
                    employees[i] = new ContractEmployee(name, fixed, tax);
                    break;
            }
        }

        System.out.println("\n========= ALL PAYSLIPS =========");

        for (Employee emp : employees) {
            emp.displayEmployee();
        }

        scanner.close();
    }



    private static int readPositiveInt(Scanner scanner, String message) {
        int value;

        while (true) {
            System.out.print(message);

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter again.");
                scanner.next();
                continue;
            }

            value = scanner.nextInt();
            scanner.nextLine();

            if (value <= 0) {
                System.out.println("Invalid input. Please enter again.");
            } else {
                return value;
            }
        }
    }

    private static int readMenuChoice(Scanner scanner) {
        int choice;

        while (true) {
            System.out.print("Enter Choice (1-3): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter again.");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > 3) {
                System.out.println("Invalid input. Please enter again.");
            } else {
                return choice;
            }
        }
    }

    private static double readPositiveDouble(Scanner scanner, String message) {
        double value;

        while (true) {
            System.out.print(message);

            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter again.");
                scanner.next();
                continue;
            }

            value = scanner.nextDouble();
            scanner.nextLine();

            if (value <= 0) {
                System.out.println("Invalid input. Please enter again.");
            } else {
                return value;
            }
        }
    }

    private static String readValidName(Scanner scanner, String message) {
        String name;

        while (true) {
            System.out.print(message);
            name = scanner.nextLine().trim();

            if (name.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
                return name;
            } else {
                System.out.println("Invalid input. Please enter again.");
            }
        }
    }
}
