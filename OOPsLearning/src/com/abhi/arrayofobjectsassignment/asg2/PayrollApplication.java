package com.abhi.arrayofobjectsassignment.asg2;

import java.util.Scanner;

public class PayrollApplication {

    private static int employeeIdCounter = 1000;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Employee[] employeeArray = new Employee[100];
        int index = 0;

        while (true) {

            System.out.println("\n===== Company Payroll System =====");
            System.out.println("1. Add Full Time Employee");
            System.out.println("2. Add Part Time Employee");
            System.out.println("3. Add Intern");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    String fullTimeName = readValidName(scanner);
                    double basicSalary = readPositiveDouble(scanner, "Enter Basic Salary: ");
                    double houseRentAllowance = readPositiveDouble(scanner, "Enter House Rent Allowance: ");
                    double dearnessAllowance = readPositiveDouble(scanner, "Enter Dearness Allowance: ");

                    employeeArray[index++] =
                            new FullTimeEmployee(employeeIdCounter++,
                                    fullTimeName,
                                    basicSalary,
                                    houseRentAllowance,
                                    dearnessAllowance);

                    System.out.println("Full Time Employee Added Successfully!");
                    break;

                case 2:
                    String partTimeName = readValidName(scanner);
                    int totalHoursWorked = readPositiveInteger(scanner, "Enter Total Hours Worked: ");
                    double hourlyPaymentRate = readPositiveDouble(scanner, "Enter Hourly Payment Rate: ");

                    employeeArray[index++] =
                            new PartTimeEmployee(employeeIdCounter++,
                                    partTimeName,
                                    totalHoursWorked,
                                    hourlyPaymentRate);

                    System.out.println("Part Time Employee Added Successfully!");
                    break;

                case 3:
                    String internName = readValidName(scanner);
                    double monthlyStipend = readPositiveDouble(scanner, "Enter Monthly Stipend: ");
                    double taxDeduction = readPositiveDouble(scanner, "Enter Tax Deduction: ");

                    employeeArray[index++] =
                            new Intern(employeeIdCounter++,
                                    internName,
                                    monthlyStipend,
                                    taxDeduction);

                    System.out.println("Intern Added Successfully!");
                    break;

                case 4:
                    if (index == 0) {
                        System.out.println("No employees added yet.");
                        break;
                    }

                    System.out.println("\n===== Salary Report =====");

                    for (int i = 0; i < index; i++) {
                        employeeArray[i].displayEmployeeDetails();
                        double salary =
                                employeeArray[i].calculateSalary(0);
                        System.out.println("Calculated Salary: " + salary);
                        System.out.println("----------------------------------");
                    }

                    System.out.println("Total Employees Created: "
                            + Employee.getTotalEmployeesCreated());
                    break;

                case 5:
                    System.out.println("Exiting Payroll System...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please select again.");
            }
        }
    }


    private static String readValidName(Scanner scanner) {
        while (true) {
            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine().trim();

            if (name.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
                return name;
            } else {
                System.out.println("Invalid name. Enter letters only (single spaces allowed).");
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


    private static int readPositiveInteger(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter integer value.");
                scanner.next();
                continue;
            }

            int value = scanner.nextInt();
            scanner.nextLine();

            if (value > 0) {
                return value;
            } else {
                System.out.println("Value must be positive.");
            }
        }
    }
}