package com.abhi.abstactinterfaceexception.asg1;

public abstract class Loan implements EligibilityCheck {

    protected String loanId;
    protected String borrowerName;
    protected double principalAmount;
    protected double interestRate;

    static {
        System.out.println("Loan Processing System Started");
    }

    public Loan(String loanId, String borrowerName, double principalAmount, double interestRate) throws InvalidLoanException {

        if (principalAmount <= 0)
            throw new InvalidLoanException("Principal amount must be positive");

        if (interestRate <= 0)
            throw new InvalidLoanException("Interest rate must be positive");

        this.loanId = loanId;
        this.borrowerName = borrowerName;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;

        System.out.println("Loan constructor executed");
    }

    abstract double calculateRepayment();

    public void displayLoan() {
        System.out.println("Loan ID: " + loanId);
        System.out.println("Borrower: " + borrowerName);
        System.out.println("Principal: " + principalAmount);
        System.out.println("Interest Rate: " + interestRate);
    }
}
