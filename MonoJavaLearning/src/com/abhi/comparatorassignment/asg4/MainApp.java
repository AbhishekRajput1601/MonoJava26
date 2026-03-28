package com.abhi.comparatorassignment.asg4;

import java.util.Scanner;

public class MainApp {

    static Scanner scanner = new Scanner(System.in);
    static HospitalManager manager = new HospitalManager();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== HOSPITAL SYSTEM =====");
            System.out.println("1. Add General Patient");
            System.out.println("2. Add Emergency Patient");
            System.out.println("3. Process Next Patient");
            System.out.println("4. Display All Patients");
            System.out.println("5. Display Sorted by ID");
            System.out.println("6. Display Sorted by Age");
            System.out.println("7. Display By Department");
            System.out.println("8. Remove Old Patients");
            System.out.println("9. Exit");

            choice = getIntInput();

            switch (choice) {
                case 1:
                    addGeneralPatient();
                    break;
                case 2:
                    addEmergencyPatient();
                    break;
                case 3:
                    manager.processNextPatient();
                    break;
                case 4:
                    manager.displayAllPatients();
                    break;
                case 5:
                    manager.displaySortedById();
                    break;
                case 6:
                    manager.displaySortedByAge();
                    break;
                case 7:
                    System.out.print("Enter department: ");
                    String dept = scanner.next();
                    manager.displayByDepartment(dept);
                    break;
                case 8:
                    System.out.print("Enter age limit: ");
                    int age = getIntInput();
                    manager.removeOldPatients(age);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 9);
    }

    static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter valid number");
            scanner.next();
        }
        return scanner.nextInt();
    }

    static void addGeneralPatient() {
        System.out.print("Enter ID: ");
        String id = scanner.next();

        System.out.print("Enter Name: ");
        String name = scanner.next();

        System.out.print("Enter Age: ");
        int age = getIntInput();

        System.out.print("Enter Department: ");
        String dept = scanner.next();

        System.out.print("Enter Problem: ");
        String problem = scanner.next();

        Patient p = new GeneralPatient(id, name, age, dept, problem);
        manager.addPatient(p);
    }

    static void addEmergencyPatient() {
        System.out.print("Enter ID: ");
        String id = scanner.next();

        System.out.print("Enter Name: ");
        String name = scanner.next();

        System.out.print("Enter Age: ");
        int age = getIntInput();

        System.out.print("Enter Department: ");
        String dept = scanner.next();

        System.out.print("Enter Severity Level: ");
        int level = getIntInput();

        Patient p = new EmergencyPatient(id, name, age, dept, level);
        manager.addPatient(p);
    }
}