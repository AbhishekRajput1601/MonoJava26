package com.abhi.exceptionhandling.assignment.asg2;

import java.util.Scanner;

public class ArrayService {

    public void testExceptions() {

        Scanner scanner = new Scanner(System.in);
        String[] names = new String[2];
        names[0] = "Abhi";

        try {

            System.out.print("Enter index to access : ");
            int index = Integer.parseInt(scanner.nextLine());
            System.out.println("length: " + names[index].length());

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("Array index out of bounds.");

        } catch (NullPointerException e) {

            System.out.println("Null value found at that index.");

        } catch (NumberFormatException e) {

            System.out.println("Invalid index input.");

        }finally {
            scanner.close();
        }
    }
}
