package com.abhi.abstractclass.assignment.asg2;

import java.util.Scanner;

public class HospitalBillingApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int totalPatients = readValidInt(scanner);

        Patient[] patients = new Patient[totalPatients];
        int index = 0;
        int patientIdCounter = 1000;

        while (true) {

            System.out.println("\n===== Hospital Billing System =====");
            System.out.println("1. Add InPatient");
            System.out.println("2. Add OutPatient");
            System.out.println("3. Add EmergencyPatient");
            System.out.println("4. Generate All Bills");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter numeric value.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    if (checkPatientLimit(index, totalPatients)) break;
                    String inName = readValidName(scanner, "Enter Patient Name: ");
                    double roomCharges = readValidDouble(scanner, "Enter Room Charges: ");
                    patients[index++] = new InPatient(patientIdCounter++, inName, roomCharges);
                    System.out.println("InPatient added successfully.");
                    break;

                case 2:
                    if (checkPatientLimit(index, totalPatients)) break;
                    String outName = readValidName(scanner, "Enter Patient Name: ");
                    double consultationFee = readValidDouble(scanner, "Enter Consultation Fee: ");
                    patients[index++] = new OutPatient(patientIdCounter++, outName, consultationFee);
                    System.out.println("OutPatient added successfully.");
                    break;

                case 3:
                    if (checkPatientLimit(index, totalPatients)) break;
                    String emName = readValidName(scanner, "Enter Patient Name: ");
                    double emergencyFee = readValidDouble(scanner, "Enter Emergency Surcharge: ");
                    patients[index++] = new EmergencyPatient(patientIdCounter++, emName, emergencyFee);
                    System.out.println("EmergencyPatient added successfully.");
                    break;

                case 4:
                    if (index == 0) {
                        System.out.println("No patients to generate bills.");
                    } else {
                        System.out.println("\nGenerating Bills for All Patients...\n");
                        for (int i = 0; i < index; i++) {
                            patients[i].generateBill();
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting Hospital Billing System...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please enter 1-5.");
            }
        }
    }

    private static boolean checkPatientLimit(int index, int totalPatients) {
        if (index >= totalPatients) {
            System.out.println("Patient limit reached! Process existing patients first.");
            return true;
        }
        return false;
    }

    private static int readValidInt(Scanner scanner) {
        while (true) {
            System.out.print("Enter total number of patients: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter numeric value.");
                scanner.next();
                continue;
            }
            int value = scanner.nextInt();
            scanner.nextLine();
            if (value <= 0) {
                System.out.println("Value must be > 0. Enter again.");
            } else {
                return value;
            }
        }
    }

    private static double readValidDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid input! Enter numeric value.");
                scanner.next();
                continue;
            }
            double value = scanner.nextDouble();
            scanner.nextLine();
            if (value <= 0) {
                System.out.println("Value must be > 0. Enter again.");
            } else return value;
        }
    }

    private static String readValidName(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String name = scanner.nextLine().trim();
            if (name.isEmpty() || !name.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
                System.out.println("Invalid name! Only letters and spaces allowed. Enter again.");
            } else return name;
        }
    }
}