package com.abhi.comparatorassignment.asg3;

import java.util.Scanner;

public class MainApp {

    private static Scanner scanner = new Scanner(System.in);
    private static StudentManager manager = new StudentManager();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n1 Add UG Student");
            System.out.println("2 Add PG Student");
            System.out.println("3 Add Marks");
            System.out.println("4 Display All");
            System.out.println("5 Sort by Marks");
            System.out.println("6 Sort by Name");
            System.out.println("7 Remove Low Performers");
            System.out.println("8 Display by Department");
            System.out.println("9 Exit");

            choice = getInt();

            switch (choice) {
                case 1:
                    addUG();
                    break;
                case 2:
                    addPG();
                    break;
                case 3:
                    addMarks();
                    break;
                case 4:
                    manager.displayAll();
                    break;
                case 5:
                    manager.sortByMarks();
                    break;
                case 6:
                    manager.sortByName();
                    break;
                case 7:
                    System.out.print("Enter minimum marks: ");
                    double min = getDouble();
                    manager.removeLowPerformers(min);
                    break;
                case 8:
                    System.out.print("Enter department: ");
                    String dept = getString();
                    manager.displayByDepartment(dept);
                    break;
                case 9:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 9);
    }

    private static int getInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Enter valid number: ");
            }
        }
    }

    private static double getDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Enter valid number: ");
            }
        }
    }

    private static String getString() {
        while (true) {
            String input = scanner.nextLine();
            if (input.trim().length() >= 2) {
                return input;
            } else {
                System.out.print("Enter valid text: ");
            }
        }
    }

    private static void addUG() {
        System.out.print("Enter ID: ");
        String id = getString();

        System.out.print("Enter Name: ");
        String name = getString();

        System.out.print("Enter Dept: ");
        String dept = getString();

        System.out.print("Enter Year: ");
        int year = getInt();

        manager.addStudent(new UndergraduateStudent(id, name, dept, year));
    }

    private static void addPG() {
        System.out.print("Enter ID: ");
        String id = getString();

        System.out.print("Enter Name: ");
        String name = getString();

        System.out.print("Enter Dept: ");
        String dept = getString();

        System.out.print("Enter Specialization: ");
        String spec = getString();

        manager.addStudent(new PostgraduateStudent(id, name, dept, spec));
    }

    private static void addMarks() {
        System.out.print("Enter Student ID: ");
        String id = getString();

        System.out.print("Enter Subject: ");
        String subject = getString();

        System.out.print("Enter Marks: ");
        int marks = getInt();

        manager.addMarks(id, subject, marks);
    }
}