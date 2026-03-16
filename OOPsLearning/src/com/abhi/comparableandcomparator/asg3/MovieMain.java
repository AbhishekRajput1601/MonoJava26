package com.abhi.comparableandcomparator.asg3;

import java.util.*;

public class MovieMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Movie> list = new ArrayList<>();

        System.out.print("Enter number of movies: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            int year;
            while (true) {

                System.out.print("Enter year: ");

                if (scanner.hasNextInt()) {
                    year = scanner.nextInt();
                    if (year > 1900) break;
                }

                System.out.println("Invalid year!");
                scanner.nextLine();
            }

            scanner.nextLine();
            list.add(new Movie(title, year));
        }

        list.sort((m1, m2) -> {
            int yearCompare = Integer.compare(m2.year, m1.year);
            if (yearCompare == 0) return m1.title.compareTo(m2.title);

            return yearCompare;
        });

        System.out.println("\nSorted Movies:");

        for (Movie m : list)
            System.out.println(m);
    }
}