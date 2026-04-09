package module.handler;

import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner;
    private static final String PLAYER_NAME_REGEX = "[A-Za-z]+";

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter numbers only.");
            }
        }
    }

    public int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Please enter a positive number.");
        }
    }


    public String readPlayerName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Name cannot be blank.");
                continue;
            }

            if (!input.matches(PLAYER_NAME_REGEX)) {
                System.out.println("Invalid name! Use letters only without spaces, numbers, or special characters.");
                continue;
            }

            return input;
        }
    }
}

