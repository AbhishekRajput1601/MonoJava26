package com.abhi.abstactinterfaceexception.asg1;

public class CarLoan extends Loan {

    public CarLoan(String loanId, String borrowerName, double principalAmount, double interestRate) throws InvalidLoanException {
        super(loanId, borrowerName, principalAmount, interestRate);
        System.out.println("CarLoan constructor executed");
    }

    double calculateRepayment() {
        return principalAmount + (principalAmount * interestRate * 5) / 100;
    }

    public boolean checkEligibility() {
        return principalAmount <= 1000000;
    }
}