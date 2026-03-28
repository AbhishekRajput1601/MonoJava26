package com.abhi.abstactinterfaceexception.asg1;

public class HomeLoan extends Loan {

    public HomeLoan(String loanId, String borrowerName, double principalAmount, double interestRate) throws InvalidLoanException {
        super(loanId, borrowerName, principalAmount, interestRate);
        System.out.println("HomeLoan constructor executed");
    }

    double calculateRepayment() {
        return principalAmount + (principalAmount * interestRate * 10) / 100;
    }

    public boolean checkEligibility() {
        return principalAmount <= 5000000;
    }
}
