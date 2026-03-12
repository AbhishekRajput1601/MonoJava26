package com.abhi.constructorandinheritance.asg1;

class BankSystem {

    private Account[] accountArray;
    private int totalAccounts;

    public BankSystem(int capacity) {
        accountArray = new Account[capacity];
        totalAccounts = 0;
    }

    public void addAccount(Account accountObject) {

        if (totalAccounts < accountArray.length) {
            accountArray[totalAccounts] = accountObject;
            totalAccounts++;
        } else {
            System.out.println("Account storage full.");
        }
    }

    public void displayAllAccounts() {

        if (totalAccounts == 0) {
            System.out.println("No accounts available.");
            return;
        }

        for (int index = 0; index < totalAccounts; index++) {
            accountArray[index].displayDetails(); // Runtime Polymorphism
            System.out.println("-----------------------------");
        }
    }
}
