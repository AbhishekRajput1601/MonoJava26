package com.abhi.arrayofobjectsassignment.asg1;

import java.util.Scanner;

public class UniversityApp {

    private static int idCounter = 1000;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Course[] courses = new Course[100];
        int index = 0;

        while (true) {

            System.out.println("\n===== University Course System =====");
            System.out.println("1. Create Regular Course");
            System.out.println("2. Create Online Course");
            System.out.println("3. Display All Courses");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter number only.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    String name1 = readValidName(sc);
                    double base1 = readPositiveDouble(sc, "Enter Base Fee: ");
                    double labFee = readPositiveDouble(sc, "Enter Lab Fee: ");

                    courses[index++] =
                            new RegularCourse(idCounter++, name1, base1, labFee);

                    System.out.println("Regular Course Created Successfully!");
                    break;

                case 2:
                    String name2 = readValidName(sc);
                    double base2 = readPositiveDouble(sc, "Enter Base Fee: ");
                    double platformFee = readPositiveDouble(sc, "Enter Platform Fee: ");

                    courses[index++] =
                            new OnlineCourse(idCounter++, name2, base2, platformFee);

                    System.out.println("Online Course Created Successfully!");
                    break;

                case 3:
                    if (index == 0) {
                        System.out.println("No courses created yet.");
                        break;
                    }
                    System.out.println("\n===== Courses Detail =====");
                    for (int i = 0; i < index; i++) {
                        courses[i].displayCourse();
                        System.out.println("Final Fee: "
                                + courses[i].calculateFee(0));
                        System.out.println("-----------------------------");
                    }

                    System.out.println("Total Courses Created: "
                            + Course.getTotalCourses());
                    break;

                case 4:
                    System.out.println("Exiting Program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }


    private static String readValidName(Scanner sc) {
        while (true) {
            System.out.print("Enter Course Name: ");
            String name = sc.nextLine().trim();

            if (name.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
                return name;
            } else {
                System.out.println("Invalid name. Enter letters only.");
            }
        }
    }

    private static double readPositiveDouble(Scanner sc, String message) {
        while (true) {
            System.out.print(message);

            if (!sc.hasNextDouble()) {
                System.out.println("Invalid input! Enter numeric value.");
                sc.next();
                continue;
            }

            double value = sc.nextDouble();
            sc.nextLine();

            if (value > 0) {
                return value;
            } else {
                System.out.println("Value must be positive.");
            }
        }
    }
}
