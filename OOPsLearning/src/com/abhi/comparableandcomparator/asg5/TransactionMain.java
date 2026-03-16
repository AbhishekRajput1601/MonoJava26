package com.abhi.comparableandcomparator.asg5;

import java.util.*;

public class TransactionMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Transaction> list = new ArrayList<>();

        System.out.print("Enter number of transactions: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.print("Enter transaction ID: ");
            int id = sc.nextInt();

            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();

            list.add(new Transaction(id, amount));
        }

        list.sort((t1, t2) -> {

            int amountCompare = Double.compare(t2.amount, t1.amount);

            if (amountCompare == 0)
                return Integer.compare(t1.id, t2.id);

            return amountCompare;
        });

        System.out.println("\nSorted Transactions:");

        for (Transaction t : list)
            System.out.println(t);
    }
}