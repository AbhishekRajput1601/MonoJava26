package com.abhi.streamapiassignment.asg1;

import java.util.*;

public class MainApp {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        System.out.println("=== Employee Management System ===");

        int n = InputValidator.getValidInt("Enter number of employees: ");

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1));

            int id = InputValidator.getValidInt("Employee ID: ");
            String name = InputValidator.getValidString("Name: ");
            String dept = InputValidator.getValidString("Department: ");
            double salary = InputValidator.getValidDouble("Salary: ");
            int exp = InputValidator.getValidInt("Experience (years): ");
            boolean status = InputValidator.getValidBoolean("Active Status (true/false): ");

            employees.add(new Employee(id, name, dept, salary, exp, status));
        }

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Get all active employees");
            System.out.println("2. Employees with salary above threshold");
            System.out.println("3. Count employees department-wise");
            System.out.println("4. Highest paid employee");
            System.out.println("5. Names sorted by salary (desc)");
            System.out.println("6. Group employees by department");
            System.out.println("7. Average salary department-wise");
            System.out.println("8. Exit");

            int choice = InputValidator.getValidInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    EmployeeService.getActiveEmployees(employees);
                    break;
                case 2:
                    double threshold = InputValidator.getValidDouble("Enter salary threshold: ");
                    EmployeeService.getEmployeesAboveSalary(employees, threshold);
                    break;
                case 3:
                    EmployeeService.countByDepartment(employees);
                    break;
                case 4:
                    EmployeeService.getHighestPaid(employees);
                    break;
                case 5:
                    EmployeeService.getNamesSortedBySalary(employees);
                    break;
                case 6:
                    EmployeeService.groupByDepartment(employees);
                    break;
                case 7:
                    EmployeeService.averageSalaryByDepartment(employees);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

