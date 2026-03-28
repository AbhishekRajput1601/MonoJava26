package com.abhi.comparatorassignment.asg1;

import java.util.Scanner;

public class LibrarySystemMain {

    private static Scanner scanner = new Scanner(System.in);
    private static LibrarySystem library = new LibrarySystem();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== LIBRARY SYSTEM MENU =====");
            System.out.println("1. Add Academic Book");
            System.out.println("2. Add Magazine");
            System.out.println("3. Remove Book");
            System.out.println("4. Return Book");
            System.out.println("5. Add Issue Request");
            System.out.println("6. Process Next Request");
            System.out.println("7. Display All Books");
            System.out.println("8. Display Available Books");
            System.out.println("9. Display Sorted by Title");
            System.out.println("10. Display Sorted by ID");
            System.out.println("11. Exit");

            choice = getValidMenuChoice("Enter your choice (1-11): ");

            switch (choice) {
                case 1:
                    addAcademicBook();
                    break;
                case 2:
                    addMagazine();
                    break;
                case 3:
                    removeBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    addIssueRequest();
                    break;
                case 6:
                    library.processNextRequest();
                    break;
                case 7:
                    library.displayLibraryData();
                    break;
                case 8:
                    library.displayAvailableBooks();
                    break;
                case 9:
                    library.getSortedByTitle().forEach(Book::getDetails);
                    break;
                case 10:
                    library.getSortedByID().forEach(Book::getDetails);
                    break;
                case 11:
                    System.out.println("Exiting system...");
                    break;
            }

        } while (choice != 11);
    }


    private static String getValidatedInput(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Invalid input! Cannot be empty.");
            }
        }
    }

    private static int getValidatedInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid number! Enter digits only.");
            }
        }
    }

    private static int getValidMenuChoice(String message) {
        while (true) {
            int choice = getValidatedInt(message);
            if (choice >= 1 && choice <= 11) {
                return choice;
            } else {
                System.out.println("Choice must be between 1 and 11.");
            }
        }
    }


    private static String getValidBookId(String message) {
        while (true) {
            String id = getValidatedInput(message);

            if (id.length() == 4 &&
                    (id.charAt(0) == 'A' || id.charAt(0) == 'M') &&
                    Character.isDigit(id.charAt(1)) &&
                    Character.isDigit(id.charAt(2)) &&
                    Character.isDigit(id.charAt(3))) {

                return id;
            }
            System.out.println("Invalid ID! Format: A123 or M123");
        }
    }

    private static String getValidMemberId(String message) {
        while (true) {
            String id = getValidatedInput(message);

            if (id.length() == 4 && id.charAt(0) == 'S' &&
                    Character.isDigit(id.charAt(1)) &&
                    Character.isDigit(id.charAt(2)) &&
                    Character.isDigit(id.charAt(3))) {

                return id;
            }
            System.out.println("Invalid Member ID! Format: S123");
        }
    }

    private static String getValidText(String message) {
        while (true) {
            String input = getValidatedInput(message);
            boolean valid = true;

            for (char ch : input.toCharArray()) {
                if (!Character.isLetter(ch) && ch != ' ') {
                    valid = false;
                    break;
                }
            }

            if (valid && input.length() >= 2) {
                return input;
            }
            System.out.println("Only alphabets allowed.");
        }
    }

    private static String getValidMonth(String message) {
        String[] months = {"January","February","March","April","May","June",
                "July","August","September","October","November","December"};

        while (true) {
            String input = getValidatedInput(message);

            for (String m : months) {
                if (m.equalsIgnoreCase(input)) {
                    return m;
                }
            }
            System.out.println("Invalid month name.");
        }
    }

    private static String getValidEdition(String message) {
        while (true) {
            String input = getValidatedInput(message);

            if (input.length() >= 3) {
                String numberPart = input.substring(0, input.length() - 2);
                String suffix = input.substring(input.length() - 2);

                try {
                    Integer.parseInt(numberPart);

                    if (suffix.equals("st") || suffix.equals("nd") ||
                            suffix.equals("rd") || suffix.equals("th")) {
                        return input;
                    }

                } catch (Exception e) {
                    System.out.println("Error : " + e);
                }
            }

            System.out.println("Invalid edition (example: 1st, 2nd).");
        }
    }


    private static void addAcademicBook() {
        String id = getValidBookId("Enter Book ID (A123): ");
        String title = getValidText("Enter Title: ");
        String author = getValidText("Enter Author: ");
        String subject = getValidText("Enter Subject: ");
        String edition = getValidEdition("Enter Edition: ");

        library.addBook(new AcademicBook(id, title, author, subject, edition));
    }

    private static void addMagazine() {
        String id = getValidBookId("Enter Magazine ID (M123): ");
        String title = getValidText("Enter Title: ");
        String author = getValidText("Enter Publisher: ");

        int issueNo = getValidatedInt("Enter Issue Number: ");
        String month = getValidMonth("Enter Month: ");

        library.addBook(new Magazine(id, title, author, issueNo, month));
    }

    private static void removeBook() {
        String id = getValidBookId("Enter Book ID to remove: ");
        library.removeBook(id);
    }

    private static void returnBook() {
        String id = getValidBookId("Enter Book ID to return: ");
        library.returnBook(id);
    }

    private static void addIssueRequest() {
        String memberID = getValidMemberId("Enter Member ID (S123): ");
        String bookID = getValidBookId("Enter Book ID: ");

        library.addIssueRequest(new IssueRequest(memberID, bookID));
    }
}