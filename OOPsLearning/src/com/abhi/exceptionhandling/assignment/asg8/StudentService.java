package com.abhi.exceptionhandling.assignment.asg8;

import java.util.Scanner;

public class StudentService {

    public void validateStudent() throws InvalidMarksException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();

        if (studentName == null || studentName.trim().isEmpty()) {
            throw new NullPointerException("Student name cannot be null.");
        }

        System.out.print("Enter marks: ");
        int marks = Integer.parseInt(scanner.nextLine());

        if (marks < 0 || marks > 100) {
            throw new InvalidMarksException("Marks must be between 0 and 100.");
        }

        System.out.println("Student: " + studentName);
        System.out.println("Marks: " + marks);

    }
}
