package com.abhi.abstactinterfaceexception.asg2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class HospitalSystem {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Enter number of services: ");
        int size = scanner.nextInt();
        scanner.nextLine();

        HospitalService[] services = new HospitalService[size];
        int count = 0;
        int choice;

        do {

            System.out.println("\n1 General Consultation");
            System.out.println("2 Surgery");
            System.out.println("3 Diagnostic Test");
            System.out.println("4 View Services");
            System.out.println("5 Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        if (isFull(count, services)) break;
                        services[count++] = new GeneralConsultation(
                                validateId(),
                                validateName(),
                                validateFee());
                        break;

                    case 2:
                        if (isFull(count, services)) break;
                        services[count++] = new Surgery(
                                validateId(),
                                validateName(),
                                validateFee());
                        break;

                    case 3:
                        if (isFull(count, services)) break;
                        services[count++] = new DiagnosticTest(
                                validateId(),
                                validateName(),
                                validateFee());
                        break;

                    case 4:

                        if (count == 0) {
                            System.out.println("No services available");
                            break;
                        }

                        for (int i = 0; i < count; i++) {

                            services[i].displayService();

                            if (services[i].validateService()) {
                                System.out.println("Service Approved");
                                System.out.println("Total Cost: " + services[i].calculateTotalCost());
                            } else {
                                System.out.println("Service Rejected");
                            }

                            System.out.println();
                        }
                        break;

                    case 5:
                        System.out.println("Exit");
                        break;

                    default:
                        System.out.println("Invalid Choice");
                }

            } catch (InvalidServiceException e) {
                System.out.println(e.getMessage());
            }

        } while (choice != 5);
    }

    static String validateId() {
        while (true) {
            System.out.print("Enter Service ID: ");
            String id = scanner.nextLine();
            if (Pattern.matches("[A-Z]{2}\\d{3}", id)) return id;
            System.out.println("Invalid ID");
        }
    }

    static String validateName() {
        while (true) {
            System.out.print("Enter Patient Name: ");
            String name = scanner.nextLine();
            if (Pattern.matches("[A-Za-z ]+", name)) return name;
            System.out.println("Invalid Name");
        }
    }

    static double validateFee() {
        while (true) {
            try {
                System.out.print("Enter Consultation Fee: ");
                double fee = scanner.nextDouble();
                scanner.nextLine();

                if (fee > 0) return fee;

            } catch (Exception e) {
                scanner.nextLine();
            }

            System.out.println("Invalid Fee");
        }
    }

    static boolean isFull(int count, HospitalService[] services) {
        if (count >= services.length) {
            System.out.println("Service storage full");
            return true;
        }
        return false;
    }
}