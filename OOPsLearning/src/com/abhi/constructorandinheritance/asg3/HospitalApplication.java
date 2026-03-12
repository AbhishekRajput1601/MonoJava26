package com.abhi.constructorandinheritance.asg3;

import java.util.Scanner;

public class HospitalApplication {

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

        HospitalSystem hospitalSystem = new HospitalSystem(10);

        while (true) {

            System.out.println("\n===== HOSPITAL MENU =====");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Nurse");
            System.out.println("3. Display All Staff");
            System.out.println("4. Process Staff");
            System.out.println("5. Exit");

            int choice = readInteger("Enter choice: ");

            switch (choice) {

                case 1:

                    int doctorId = readInteger("Enter ID: ");
                    String doctorName = readString("Enter Name: ");
                    String department = readString("Enter Department: ");
                    String specialization = readString("Enter Specialization: ");
                    double consultationFee = readDouble("Enter Consultation Fee: ");

                    Doctor doctor = new Doctor(
                            doctorId,
                            doctorName,
                            department,
                            specialization,
                            consultationFee
                    );

                    hospitalSystem.addStaff(doctor);
                    break;

                case 2:

                    int nurseId = readInteger("Enter ID: ");
                    String nurseName = readString("Enter Name: ");
                    String nurseDepartment = readString("Enter Department: ");
                    String shiftType = readString("Enter Shift Type: ");
                    String wardAssigned = readString("Enter Ward Assigned: ");

                    Nurse nurse = new Nurse(
                            nurseId,
                            nurseName,
                            nurseDepartment,
                            shiftType,
                            wardAssigned
                    );

                    hospitalSystem.addStaff(nurse);
                    break;

                case 3:
                    hospitalSystem.displayAllStaff();
                    break;

                case 4:
                    hospitalSystem.processStaff();
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