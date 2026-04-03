package solid.assignment.ocp;

import java.util.Scanner;

public class InputValidator {
    private static Scanner sc = new Scanner(System.in);

    public static int getChoice(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }
}
