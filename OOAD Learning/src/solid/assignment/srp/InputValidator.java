package solid.assignment.srp;

import java.util.Scanner;

public class InputValidator {
    private static Scanner scanner = new Scanner(System.in);

    public static String getString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public static int getInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number.");
            }
        }
    }
}
