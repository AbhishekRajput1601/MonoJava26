package com.abhi.encapsulation.constructorAssignment.asg2;

import java.util.Scanner;

public class InsuranceApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Claim Type:");
        System.out.println("1. Normal Claim");
        System.out.println("2. Corporate Claim");
        System.out.print("Enter choice: ");
        int type = scanner.nextInt();
        scanner.nextLine();

        String policyNumber;
        do {
            System.out.print("Enter Policy Number: ");
            policyNumber = scanner.nextLine();

            if (policyNumber.isBlank()) {
                System.out.println("Policy number cannot be empty.");
            }

        } while (policyNumber.isBlank());

        System.out.print("Enter Claim Amount: ");
        double claimAmount = scanner.nextDouble();

        InsuranceClaim claim;

        if (type == 2) {
            claim = new CorporateClaim(policyNumber, claimAmount);
        } else {
            claim = new InsuranceClaim(policyNumber, claimAmount);
        }

        System.out.println("\nClaim Created Successfully!");
        System.out.println("Claim ID: " + claim.getClaimId());
        System.out.println("Policy Number: " + claim.getPolicyNumber());
        System.out.println("Claim Amount: ₹" + claim.getClaimAmount());
        System.out.println("Approved Amount: ₹" + claim.getApprovedAmount());
        System.out.println("Status: " + claim.getStatus());

        scanner.close();
    }
}
