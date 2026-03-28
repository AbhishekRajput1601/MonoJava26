package com.abhi.arrays.assignment;

import java.util.Scanner;

public class StudentPerformanceHeatmap {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int SUBJECT_COUNT = 5;

    public static void main(String[] args) {

        System.out.print("Enter number of students: ");
        int numberOfStudents = getValidPositiveInt();

        int[][] marks = new int[numberOfStudents][SUBJECT_COUNT];
        double[] subjectTotals = new double[SUBJECT_COUNT];
        int distinctionCount = 0;

        // Input marks
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\nEnter marks for Student " + (i + 1));

            for (int j = 0; j < SUBJECT_COUNT; j++) {
                System.out.print("Subject " + (j + 1) + " marks (0-100): ");
                marks[i][j] = getValidMarks();
                subjectTotals[j] += marks[i][j];
            }
        }

        System.out.println("\n===== Student Results =====");


        for (int i = 0; i < numberOfStudents; i++) {

            boolean hasFailedSubject = false;
            int totalMarks = 0;

            for (int j = 0; j < SUBJECT_COUNT; j++) {
                if (marks[i][j] < 35) {
                    hasFailedSubject = true;
                }
                totalMarks += marks[i][j];
            }

            double average = totalMarks / (double) SUBJECT_COUNT;

            String result;

            if (hasFailedSubject) {
                result = "Fail";
            } else if (average >= 85) {
                result = "Distinction";
                distinctionCount++;
            } else if (average >= 60) {
                result = "First Class";
            } else if (average >= 50) {
                result = "Second Class";
            } else {
                result = "Fail";
            }

            System.out.printf("Student %d → Average: %.2f → %s%n",
                    (i + 1), average, result);
        }

        int bestSubjectIndex = 0;
        double highestAverage = subjectTotals[0] / numberOfStudents;

        for (int i = 1; i < SUBJECT_COUNT; i++) {
            double subjectAverage = subjectTotals[i] / numberOfStudents;
            if (subjectAverage > highestAverage) {
                highestAverage = subjectAverage;
                bestSubjectIndex = i;
            }
        }

        System.out.println("\n===== Summary =====");
        System.out.println("Total Distinctions: " + distinctionCount);
        System.out.println("Subject with Highest Average: Subject "
                + (bestSubjectIndex + 1)
                + " (Average: "
                + String.format("%.2f", highestAverage) + ")");
    }


    private static int getValidPositiveInt() {
        int value;

        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Enter a valid integer: ");
                scanner.next();
                continue;
            }

            value = scanner.nextInt();

            if (value <= 0) {
                System.out.print("Number must be greater than 0. Try again: ");
                continue;
            }

            return value;
        }
    }

    private static int getValidMarks() {
        int value;

        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Enter marks between 0 and 100: ");
                scanner.next();
                continue;
            }

            value = scanner.nextInt();

            if (value < 0 || value > 100) {
                System.out.print("Marks must be between 0 and 100. Try again: ");
                continue;
            }

            return value;
        }
    }
}
