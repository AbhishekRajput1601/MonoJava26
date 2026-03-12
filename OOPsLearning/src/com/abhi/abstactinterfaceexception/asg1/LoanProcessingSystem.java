package com.abhi.abstactinterfaceexception.asg1;

import java.util.*;
import java.util.regex.*;

public class LoanProcessingSystem {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("\nEnter number of loans to store: ");
        int size = scanner.nextInt();
        scanner.nextLine();

        if (size <= 0) {
            System.out.println("Invalid array size");
            return;
        }

        Loan[] loans = new Loan[size];
        int count = 0;
        int choice;

        do {
            System.out.println("==== Loan System ====");
            System.out.println("1 Add Home Loan");
            System.out.println("2 Add Car Loan");
            System.out.println("3 Add Education Loan");
            System.out.println("4 View Loans");
            System.out.println("5 Exit");

            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        if (isStorageFull(count, loans)) break;

                        loans[count++] = new HomeLoan(
                                validateLoanId(),
                                validateName(),
                                validateAmount(),
                                validateRate());
                        break;

                    case 2:
                        if (isStorageFull(count, loans)) break;

                        loans[count++] = new CarLoan(
                                validateLoanId(),
                                validateName(),
                                validateAmount(),
                                validateRate());
                        break;

                    case 3:
                        if (isStorageFull(count, loans)) break;

                        loans[count++] = new EducationLoan(
                                validateLoanId(),
                                validateName(),
                                validateAmount(),
                                validateRate());
                        break;

                    case 4:

                        if (count == 0) {
                            System.out.println("No loans available");
                            break;
                        }

                        System.out.println("Loan Details -> ");
                        for (int i = 0; i < count; i++) {

                            loans[i].displayLoan();

                            if (loans[i].checkEligibility()) {
                                System.out.println("Eligible");
                                System.out.println("Repayment: " + loans[i].calculateRepayment());
                            } else {
                                System.out.println("Not Eligible");
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

            } catch (InvalidLoanException e) {
                System.out.println(e.getMessage());
            }

        } while (choice != 5);
    }

    static String validateLoanId() {
        while (true) {
            System.out.print("Enter Loan ID: ");
            String id = scanner.nextLine();
            if (Pattern.matches("[A-Z]{2}\\d{3}", id)) return id;
            System.out.println("Invalid Loan ID");
        }
    }

    static String validateName() {
        while (true) {
            System.out.print("Enter Borrower Name: ");
            String name = scanner.nextLine();
            if (Pattern.matches("[A-Za-z ]+", name)) return name;
            System.out.println("Invalid Name");
        }
    }

    static double validateAmount() {
        while (true) {
            System.out.print("Enter Principal Amount: ");
            String input = scanner.nextLine();
            if (Pattern.matches("\\d+(\\.\\d+)?", input))
                return Double.parseDouble(input);

            System.out.println("Invalid Amount");
        }
    }

    static double validateRate() {
        while (true) {
            System.out.print("Enter Interest Rate: ");
            String input = scanner.nextLine();

            if (Pattern.matches("\\d+(\\.\\d+)?", input)) {
                double rate = Double.parseDouble(input);
                if (rate > 0 && rate < 100) return rate;
            }
            System.out.println("Invalid Rate");
        }
    }

    static boolean isStorageFull(int count, Loan[] loans) {
        if (count >= loans.length) {
            System.out.println("Loan storage is full");
            return true;
        }
        return false;
    }
}