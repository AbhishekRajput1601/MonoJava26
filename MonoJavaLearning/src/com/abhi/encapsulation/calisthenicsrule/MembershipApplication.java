package com.abhi.encapsulation.calisthenicsrule;

public class MembershipApplication {

    public static void main(String[] args) {

        CustomerAccount customerAccount = new CustomerAccount(1500);
        customerAccount.upgradeMembership();

        System.out.println(customerAccount.membershipLevel());
    }
}