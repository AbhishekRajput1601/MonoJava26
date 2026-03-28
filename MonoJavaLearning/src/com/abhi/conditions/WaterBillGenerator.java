package com.abhi.conditions;

import java.util.Scanner;

public class WaterBillGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of units consumed: ");
        int units = scanner.nextInt();

        if(units<0){
            System.out.println("Please give correct unit");
        }

        int meterCharge = 75;
        int charge;

        if (units <= 100) {
            charge = units * 5;
        } else if (units <= 250) {
            charge = units * 10;
        } else {
            charge = units * 20;
        }

        int totalWaterBill = charge + meterCharge;

        System.out.println("Total water bill = " + totalWaterBill);

        scanner.close();
    }
}

