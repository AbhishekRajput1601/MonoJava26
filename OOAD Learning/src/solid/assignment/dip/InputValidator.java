package solid.assignment.dip;

import java.util.Scanner;

public class InputValidator {
    private static Scanner scanner = new Scanner(System.in);

    public static String getInput(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }
}
