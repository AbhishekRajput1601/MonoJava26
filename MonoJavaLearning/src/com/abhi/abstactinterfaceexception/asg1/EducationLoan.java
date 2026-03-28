package com.abhi.abstactinterfaceexception.asg1;

public class EducationLoan extends Loan {

    public EducationLoan(String loanId, String borrowerName, double principalAmount, double interestRate) throws InvalidLoanException {
        super(loanId, borrowerName, principalAmount, interestRate);
        System.out.println("EducationLoan constructor executed");
    }

    double calculateRepayment() {
        return principalAmount + (principalAmount * interestRate * 3) / 100;
    }

    public boolean checkEligibility() {
        return principalAmount <= 2000000;
    }
}