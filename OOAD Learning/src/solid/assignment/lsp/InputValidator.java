package solid.assignment.lsp;

import java.util.Scanner;

public class InputValidator {
    private static Scanner sc = new Scanner(System.in);

    public static double getAmount(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }
}
