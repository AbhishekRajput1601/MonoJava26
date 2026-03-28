package com.abhi.inheritance.assignment.asg3;

import java.util.Scanner;

public class NotificationApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            int choice = readMenuChoice(scanner);

            if (choice == 4) {
                System.out.println("Exiting program...");
                break;
            }

            String recipient = "";
            String message;

            switch (choice) {

                case 1:
                    recipient = readValidEmail(scanner);
                    message = readNonEmptyMessage(scanner);
                    Notification email =
                            new EmailNotification(recipient, message);
                    email.send();
                    break;

                case 2:
                    recipient = readValidMobile(scanner);
                    message = readNonEmptyMessage(scanner);
                    Notification sms =
                            new SMSNotification(recipient, message);
                    sms.send();
                    break;

                case 3:
                    recipient = readNonEmptyName(scanner, "Enter User Name: ");
                    message = readNonEmptyMessage(scanner);
                    Notification push =
                            new PushNotification(recipient, message);
                    push.send();
                    break;
            }
        }

        scanner.close();
    }


    private static int readMenuChoice(Scanner scanner) {
        int choice;

        while (true) {
            System.out.println("\n===== Notification Menu =====");
            System.out.println("1. Email Notification");
            System.out.println("2. SMS Notification");
            System.out.println("3. Push Notification");
            System.out.println("4. Exit");

            System.out.print("Enter choice (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter again.");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > 4) {
                System.out.println("Invalid input. Please enter again.");
            } else {
                return choice;
            }
        }
    }

    private static String readValidEmail(Scanner scanner) {
        String email;

        while (true) {
            System.out.print("Enter Email: ");
            email = scanner.nextLine().trim();

            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            } else {
                System.out.println("Invalid email format. Please enter again.");
            }
        }
    }


    private static String readValidMobile(Scanner scanner) {
        String mobile;

        while (true) {
            System.out.print("Enter Mobile Number (10 digits): ");
            mobile = scanner.nextLine().trim();

            if (mobile.matches("^\\d{10}$")) {
                return mobile;
            } else {
                System.out.println("Invalid mobile number. Please enter again.");
            }
        }
    }

    private static String readNonEmptyName(Scanner scanner, String prompt) {
        String input;

        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();

            if (input.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter again.");
            }
        }
    }


    private static String readNonEmptyMessage(Scanner scanner) {
        String msg;

        while (true) {
            System.out.print("Enter Message: ");
            msg = scanner.nextLine().trim();

            if (!msg.isEmpty()) {
                return msg;
            } else {
                System.out.println("Message cannot be empty. Please enter again.");
            }
        }
    }
}