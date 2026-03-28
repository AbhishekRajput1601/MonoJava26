package com.abhi.exceptionhandling.assignment.asg3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMService {
    private double accountBalance = 1000;

    public void withdrawAmount(){
        Scanner scanner = new Scanner(System.in);

        try{
            System.out.print("Enter Amount : ");
            double amount = scanner.nextDouble();

            if (amount > accountBalance){
                throw new ArithmeticException("Insufficient Balance");
            }

            accountBalance = accountBalance - amount;
            System.out.println("Withdrawal is done remaining amount is : " + accountBalance);
        }catch (InputMismatchException e){
            System.out.println("Invalid Input");
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("Payment session ended.");
        }
    }
}
