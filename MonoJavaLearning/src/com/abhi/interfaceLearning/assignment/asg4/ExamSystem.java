package com.abhi.interfaceLearning.assignment.asg4;

import java.util.Scanner;

public class ExamSystem {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            showMenu();
            int choice = getValidChoice();

            switch (choice) {

                case 1:
                    processExam(new TheoryExam(getValidMarks()));
                    break;

                case 2:
                    processExam(new PracticalExam(getValidMarks()));
                    break;

                case 3:
                    processExam(new OnlineQuiz(getValidMarks()));
                    break;

                case 4:
                    System.out.println("Exiting Exam System.");
                    System.exit(0);
                    break;
            }
        }
    }

    public static void processExam(ExamEvaluator evaluator) {

        double marks = evaluator.evaluateMarks();
        String grade = evaluator.calculateGrade(marks);

        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
    }

    public static void showMenu() {

        System.out.println("\n===== EXAM SYSTEM =====");
        System.out.println("1. Theory Exam");
        System.out.println("2. Practical Exam");
        System.out.println("3. Online Quiz");
        System.out.println("4. Exit");
    }

    public static int getValidChoice() {

        while (true) {

            System.out.print("Select option (1-4): ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Invalid input. Enter numbers only.");
                continue;
            }

            int choice = scanner.nextInt();

            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice.");
                continue;
            }

            return choice;
        }
    }

    public static double getValidMarks() {

        while (true) {

            System.out.print("Enter marks (0-100): ");

            if (!scanner.hasNextDouble()) {
                scanner.next();
                System.out.println("Invalid input. Enter numeric value.");
                continue;
            }

            double marks = scanner.nextDouble();

            if (marks < 0 || marks > 100) {
                System.out.println("Marks must be between 0 and 100.");
                continue;
            }

            return marks;
        }
    }
}