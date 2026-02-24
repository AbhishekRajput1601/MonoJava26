package com.abhi.encapsulation.calisthenicsrule;

class OldCustomerAccount {

    private double accBal;
    private String memLvl;

    public OldCustomerAccount(double accBal) {
        this.accBal = accBal;
        this.memLvl = "STANDARD";
    }


    public double getAccountBalance() {
        return accBal;
    }

    public String getMembershipLevel() {
        return memLvl;
    }

    public void setMembershipLevel(String memLvl) {
        this.memLvl = memLvl;
    }
}