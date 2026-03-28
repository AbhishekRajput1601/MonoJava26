package com.abhi.encapsulation.test;

import java.util.Scanner;
import com.abhi.encapsulation.module.InsuranceClaim;

public class InsuranceApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Policy Number: ");
        String policyNumber = scanner.nextLine();

        System.out.print("Enter Claim Amount: ");
        double claimAmount = scanner.nextDouble();

        InsuranceClaim claim = new InsuranceClaim(policyNumber, claimAmount);

        int choice;

        do {
            System.out.println("\n===== Insurance Claim Menu =====");
            System.out.println("1. View Claim Details");
            System.out.println("2. Approve Claim");
            System.out.println("3. Reject Claim");
            System.out.println("4. Settle Claim");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Claim ID: " + claim.getClaimId());
                    System.out.println("Policy Number: " + claim.getPolicyNumber());
                    System.out.println("Claim Amount: " + claim.getClaimAmount());
                    System.out.println("Approved Amount: " + claim.getApprovedAmount());
                    System.out.println("Status: " + claim.getClaimStatus());
                    break;

                case 2:
                    System.out.print("Enter Approved Amount: ");
                    double approvedAmount = scanner.nextDouble();
                    claim.approveClaim(approvedAmount);
                    break;

                case 3:
                    claim.rejectClaim();
                    break;

                case 4:
                    claim.settleClaim();
                    break;

                case 5:
                    System.out.println("Exiting Insurance System...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
