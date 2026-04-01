package com.abhi.streamapiassignment.asg5;

import java.util.*;

public class MainPatientApp {

    public static void main(String[] args) {

        List<Patient> patients = new ArrayList<>();

        System.out.println("=== Hospital Patient Record Analyzer ===");

        int n = InputValidator.getValidInt("Enter number of patients: ");

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Patient " + (i + 1));

            int id = InputValidator.getValidInt("Patient ID: ");
            String name = InputValidator.getValidString("Name: ");
            int age = InputValidator.getValidInt("Age: ");
            String disease = InputValidator.getValidString("Disease: ");
            boolean admitted = InputValidator.getValidBoolean("Admitted (true/false): ");
            double bill = InputValidator.getValidDouble("Bill Amount: ");

            patients.add(new Patient(id, name, age, disease, admitted, bill));
        }

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Admitted Patients");
            System.out.println("2. Group by Disease");
            System.out.println("3. Partition Admitted/Non-Admitted");
            System.out.println("4. Highest Bill Patient");
            System.out.println("5. Average Bill");
            System.out.println("6. Patients Age > 60");
            System.out.println("7. Disease -> Patient Names Map");
            System.out.println("8. Exit");

            int choice = InputValidator.getValidInt("Enter choice: ");

            switch (choice) {
                case 1:
                    PatientService.getAdmittedPatients(patients);
                    break;
                case 2:
                    PatientService.groupByDisease(patients);
                    break;
                case 3:
                    PatientService.partitionByAdmission(patients);
                    break;
                case 4:
                    PatientService.getHighestBill(patients);
                    break;
                case 5:
                    PatientService.getAverageBill(patients);
                    break;
                case 6:
                    PatientService.getSeniorPatientNames(patients);
                    break;
                case 7:
                    PatientService.diseaseToNamesMap(patients);
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
