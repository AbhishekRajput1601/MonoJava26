package com.abhi.encapsulation.constructorAssignment.asg1;

public class PremiumAccount extends BankAccount {

    private static final double BONUS_AMOUNT = 1000;

    public PremiumAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance + BONUS_AMOUNT);
    }

    public PremiumAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName, BONUS_AMOUNT);
    }
}
