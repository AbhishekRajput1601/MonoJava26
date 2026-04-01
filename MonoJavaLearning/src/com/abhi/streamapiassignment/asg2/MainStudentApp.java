package com.abhi.streamapiassignment.asg2;

import java.util.*;

public class MainStudentApp {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        System.out.println("=== Student Result Processing System ===");

        int n = InputValidator.getValidInt("Enter number of students: ");

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Student " + (i + 1));

            int roll = InputValidator.getValidInt("Roll No: ");
            String name = InputValidator.getValidString("Name: ");
            String std = InputValidator.getValidString("Class: ");
            double marks = InputValidator.getValidDouble("Marks: ");
            String section = InputValidator.getValidString("Section: ");

            students.add(new Student(roll, name, std, marks, section));
        }

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Passed Students");
            System.out.println("2. Top 3 Students");
            System.out.println("3. Group by Section");
            System.out.println("4. Count Section-wise");
            System.out.println("5. Average Marks Section-wise");
            System.out.println("6. Uppercase Names");
            System.out.println("7. Check Full Marks");
            System.out.println("8. Exit");

            int choice = InputValidator.getValidInt("Enter choice: ");

            switch (choice) {
                case 1:
                    double min = InputValidator.getValidDouble("Enter passing marks: ");
                    StudentService.getPassedStudents(students, min);
                    break;

                case 2:
                    StudentService.getTop3Students(students);
                    break;

                case 3:
                    StudentService.groupBySection(students);
                    break;

                case 4:
                    StudentService.countBySection(students);
                    break;

                case 5:
                    StudentService.avgMarksBySection(students);
                    break;

                case 6:
                    StudentService.getUppercaseNames(students);
                    break;

                case 7:
                    StudentService.hasFullMarks(students);
                    break;

                case 8:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
