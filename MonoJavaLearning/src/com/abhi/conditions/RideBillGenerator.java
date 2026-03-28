package com.abhi.conditions;

import java.util.Scanner;

public class RideBillGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter height (in cm): ");
        int height = scanner.nextInt();

        if (height <= 120) {
            System.out.println("Can't ride");
            scanner.close();
            return;
        }

        int bill = 0;

        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        if(age<0){
            System.out.println("Please give correct unit");
        }

        if (age < 12) {
            bill = 5;
        }
        else if (age >= 12 && age <= 18) {
            bill = 7;
        }
        else if (age >= 45 && age <= 55) {   // Must come BEFORE age > 18
            bill = 0;
        }
        else {
            bill = 12;
        }

        System.out.print("Do you want photos? (yes/no): ");
        String wantPhotos = scanner.next();

        if (wantPhotos.equalsIgnoreCase("yes")) {
            bill += 3;
        }

        System.out.println("The total bill is $" + bill);

        scanner.close();
    }
}

