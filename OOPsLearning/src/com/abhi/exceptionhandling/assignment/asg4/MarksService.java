package com.abhi.exceptionhandling.assignment.asg4;

import java.util.Scanner;

public class MarksService {

    public void validateMarks() {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter marks: ");
            int marks = Integer.parseInt(scanner.nextLine());

            if (marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100.");
            }

            System.out.println("Valid marks.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid marks input.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}