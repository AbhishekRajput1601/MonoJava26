package com.abhi.encapsulation.calisthenicsrule;

public class OldMembershipApplication {

    public static void main(String[] args) {

        OldCustomerAccount customerAccount = new OldCustomerAccount(1500);

        if (customerAccount.getAccountBalance() > 1000) {
            customerAccount.setMembershipLevel("PREMIUM");
        } else {
            customerAccount.setMembershipLevel("STANDARD");
        }

        System.out.println(customerAccount.getMembershipLevel());
    }
}