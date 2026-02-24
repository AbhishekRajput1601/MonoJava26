package com.abhi.encapsulation.constructor.enumexample;

import java.util.Scanner;

enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

public class TestEnum {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Day today = null;
        boolean isValid = false;

        while (!isValid) {

            System.out.print("Enter a day (e.g., MONDAY) : ");
            String inputDay = scanner.nextLine().toUpperCase();

            for (Day d : Day.values()) {
                if (d.name().equals(inputDay)) {
                    today = d;
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                System.out.println("Invalid day entered! Please try again.\n");
            }
        }

        if (today == Day.MONDAY) {
            System.out.println("Start of the week!");
        }
        else if (today == Day.SATURDAY || today == Day.SUNDAY) {
            System.out.println("Weekend!");
        }
        else {
            System.out.println("Weekday!");
        }

        scanner.close();
    }
}
