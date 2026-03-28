package com.abhi.interfaceLearning.assignment.asg3;

import java.util.Scanner;

public class SmartHomeController {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Controllable[] devices = {
                new Light(),
                new Fan(),
                new TV(),
                new Speaker()
        };

        while (true) {

            showMenu();
            int choice = getValidChoice();

            if (choice == 5) {
                System.out.println("Exiting Smart Home Controller.");
                System.exit(0);
            }

            Controllable selectedDevice = devices[choice - 1];
            controlDevice(selectedDevice);
        }
    }

    public static void showMenu() {

        System.out.println("\n===== SMART HOME DEVICE CONTROLLER =====");
        System.out.println("1. Light");
        System.out.println("2. Fan");
        System.out.println("3. TV");
        System.out.println("4. Speaker");
        System.out.println("5. Exit");
    }

    public static int getValidChoice() {

        while (true) {

            System.out.print("Select device (1-5): ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Invalid input. Enter numbers only.");
                continue;
            }

            int choice = scanner.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Select between 1 and 5.");
                continue;
            }

            return choice;
        }
    }

    public static void controlDevice(Controllable device) {

        while (true) {

            showDeviceMenu();
            int action = getValidAction();

            switch (action) {

                case 1:
                    device.turnOn();
                    break;

                case 2:
                    String mode = getModeInput();
                    device.setMode(mode);
                    break;

                case 3:
                    device.turnOff();
                    break;

                case 4:
                    return;
            }
        }
    }

    public static void showDeviceMenu() {

        System.out.println("\n--- Device Control Menu ---");
        System.out.println("1. Turn ON");
        System.out.println("2. Set Mode");
        System.out.println("3. Turn OFF");
        System.out.println("4. Go Back To Select Device");
    }

    public static int getValidAction() {

        while (true) {

            System.out.print("Select action (1-4): ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Invalid input. Enter numbers only.");
                continue;
            }

            int action = scanner.nextInt();

            if (action < 1 || action > 4) {
                System.out.println("Invalid choice. Select between 1 and 4.");
                continue;
            }

            return action;
        }
    }

    public static String getModeInput() {

        scanner.nextLine();

        while (true) {

            System.out.print("Enter mode (letters only): ");
            String mode = scanner.nextLine().trim();

            if (mode.isEmpty()) {
                System.out.println("Mode cannot be empty.");
                continue;
            }

            if (!mode.matches("[a-zA-Z]+")) {
                System.out.println("Invalid mode. Only alphabetic characters allowed.");
                continue;
            }

            return mode;
        }
    }
}