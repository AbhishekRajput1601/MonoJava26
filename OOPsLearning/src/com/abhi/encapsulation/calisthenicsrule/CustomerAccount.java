package com.abhi.encapsulation.calisthenicsrule;

class CustomerAccount {

    private double accountBalance;
    private String membershipLevel;

    public CustomerAccount(double accountBalance) {
        this.accountBalance = accountBalance;
        this.membershipLevel = "STANDARD";
    }


    public void upgradeMembership() {
        if (!isEligibleForPremium()) return;
        membershipLevel = "PREMIUM";
    }

    private boolean isEligibleForPremium() {
        return accountBalance > 1000;
    }

    public String membershipLevel() {
        return membershipLevel;
    }
}