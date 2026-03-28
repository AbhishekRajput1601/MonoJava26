package com.abhi.constructorandinheritance.asg2;

import java.util.Scanner;

public class StudentApplication {

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

    public static void main(String[] args) {

        InstituteSystem instituteSystem = new InstituteSystem(10);

        while (true) {

            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1. Add Regular Student");
            System.out.println("2. Add Scholarship Student");
            System.out.println("3. Display All Students");
            System.out.println("4. Process Students");
            System.out.println("5. Exit");

            int choice = readInteger("Enter choice: ");

            switch (choice) {

                case 1:

                    int regularId = readInteger("Enter Student ID: ");
                    String regularName = readString("Enter Name: ");
                    String regularCourse = readString("Enter Course: ");
                    double attendance = readDouble("Enter Attendance Percentage: ");

                    RegularStudent regularStudent =
                            new RegularStudent(
                                    regularId,
                                    regularName,
                                    regularCourse,
                                    attendance
                            );

                    instituteSystem.addStudent(regularStudent);
                    break;

                case 2:

                    int scholarshipId = readInteger("Enter Student ID: ");
                    String scholarshipName = readString("Enter Name: ");
                    String scholarshipCourse = readString("Enter Course: ");
                    double scholarshipAmount = readDouble("Enter Scholarship Amount: ");

                    ScholarshipStudent scholarshipStudent =
                            new ScholarshipStudent(
                                    scholarshipId,
                                    scholarshipName,
                                    scholarshipCourse,
                                    scholarshipAmount
                            );

                    instituteSystem.addStudent(scholarshipStudent);
                    break;

                case 3:
                    instituteSystem.displayAllStudents();
                    break;

                case 4:
                    instituteSystem.processStudents();
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
