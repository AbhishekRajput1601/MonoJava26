package model.validationmodel;

import model.modelservices.InventoryService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputValidator {

    public static String readString(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }
            return input;
        }
    }

    public static int readInt(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine());
                if (value < 0) throw new InvalidInputException("Negative not allowed");
                return value;
            } catch (Exception e) {
                System.out.println("Enter a valid non-negative integer.");
            }
        }
    }

    public static double readDouble(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine());
                if (value < 0) throw new InvalidInputException("Negative not allowed");
                return value;
            } catch (Exception e) {
                System.out.println("Enter a valid non-negative number.");
            }
        }
    }

    public static boolean readYesNo(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) return true;
            if (input.equalsIgnoreCase("no")) return false;
            System.out.println("Enter yes or no.");
        }
    }

    public static LocalDate readDate(Scanner scanner, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            try {
                System.out.print(message + " (dd-MM-yyyy): ");
                String input = scanner.nextLine();

                LocalDate date = LocalDate.parse(input, formatter);

                if (date.isBefore(LocalDate.now())) {
                    throw new InvalidInputException("Expiry date cannot be in the past.");
                }

                return date;

            } catch (Exception e) {
                System.out.println("Invalid date. Please enter in dd-MM-yyyy format.");
            }
        }
    }
    public static int readChoice(Scanner scanner, String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine());

                if (value < min || value > max) {
                    System.out.println("Invalid choice, please make choice between " + min + " to " + max);
                    continue;
                }

                return value;

            } catch (Exception e) {
                System.out.println("Enter a valid non-negative integer.");
            }
        }
    }

    public static int readReorderLevel(Scanner scanner, String message, int productQuantity) {
        while (true) {
            try {
                System.out.print(message);
                int reorderLevel = Integer.parseInt(scanner.nextLine());

                if (reorderLevel < 0) {
                    throw new InvalidInputException("Reorder level cannot be negative.");
                }

                if (reorderLevel >= productQuantity) {
                    throw new InvalidInputException("Reorder level must be less than product quantity.");
                }

                return reorderLevel;

            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Enter a valid non-negative integer.");
            }
        }
    }

    public static String readExistingProductName(
            Scanner scanner,
            String message,
            InventoryService inventoryService
    ) {
        while (true) {
            String productName = readString(scanner, message);

            try {
                inventoryService.getProductQuantity(productName);
                return productName;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Please re-enter a valid product name.");
            }
        }
    }
}