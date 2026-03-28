package com.abhi.comparableandcomparator.asg1;

import java.util.*;

public class CandidateMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Candidate> list = new ArrayList<>();

        System.out.print("Enter number of candidates: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for(int i=0; i<n; i++){
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            int age;
            while (true){
                System.out.print("Enter age: ");
                if (scanner.hasNextInt()) {
                    age = scanner.nextInt();
                    if (age > 0) break;
                }
                System.out.println("Invalid age!");
                scanner.nextLine();
            }
            scanner.nextLine();

            list.add(new Candidate(name, age));
            }

            list.sort(new Comparator<Candidate>() {
                public int compare(Candidate c1, Candidate c2) {
                    int nameCompare = c1.name.compareTo(c2.name);
                    if (nameCompare == 0) return Integer.compare(c1.age, c2.age);
                    return nameCompare;
                }
            });

        System.out.println("\nSorted Candidates:");
        for (Candidate c : list)
            System.out.println(c);

        }

}

