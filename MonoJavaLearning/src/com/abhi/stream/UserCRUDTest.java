package com.abhi.stream;

import java.util.Scanner;

public class UserCRUDTest {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        UserCRUD service = new UserCRUD();

        int choice;

        do {
            System.out.println("\n===== USER MENU =====");
            System.out.println("1. Create User");
            System.out.println("2. Read Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter user name: ");
                    String name = sc.nextLine();
                    service.createUser(name);
                    break;

                case 2:
                    System.out.println("Users present in the file -> ");
                    service.readUsers();
                    System.out.println("User created successfully");
                    break;
                case 3:
                    System.out.print("Enter old name: ");
                    String oldName = sc.nextLine();

                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();

                    service.updateUser(oldName, newName);
                    break;

                case 4:
                    System.out.print("Enter name to delete: ");
                    String deleteName = sc.nextLine();
                    service.deleteUser(deleteName);
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}