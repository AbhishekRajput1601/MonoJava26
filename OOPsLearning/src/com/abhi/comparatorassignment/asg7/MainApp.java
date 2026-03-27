package com.abhi.comparatorassignment.asg7;

import java.util.Scanner;

public class MainApp {

    static Scanner scanner = new Scanner(System.in);
    static CourseManager manager = new CourseManager();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== COURSE SYSTEM =====");
            System.out.println("1. Add Regular Participant");
            System.out.println("2. Add Corporate Participant");
            System.out.println("3. Add to Waiting List");
            System.out.println("4. Process Waiting List");
            System.out.println("5. Display All Participants");
            System.out.println("6. Display Sorted by ID");
            System.out.println("7. Display Sorted by Name");
            System.out.println("8. Display Sorted by Track");
            System.out.println("9. Display By Track");
            System.out.println("10. Remove Participants by Track");
            System.out.println("11. Exit");

            choice = getIntInput();

            switch (choice) {

                case 1:
                    addRegularParticipant();
                    break;

                case 2:
                    addCorporateParticipant();
                    break;

                case 3:
                    addToWaiting();
                    break;

                case 4:
                    manager.processWaitingList();
                    break;

                case 5:
                    manager.displayAllParticipants();
                    break;

                case 6:
                    manager.displaySortedById();
                    break;

                case 7:
                    manager.displaySortedByName();
                    break;

                case 8:
                    manager.displaySortedByTrack();
                    break;

                case 9:
                    System.out.print("Enter track: ");
                    String track = scanner.next();
                    manager.displayByTrack(track);
                    break;

                case 10:
                    System.out.print("Enter track to remove: ");
                    String t = scanner.next();
                    manager.removeParticipantsByTrack(t);
                    break;

                case 11:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 11);
    }

    static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter valid number");
            scanner.next();
        }
        return scanner.nextInt();
    }

    static void addRegularParticipant() {

        System.out.print("Enter ID: ");
        String id = scanner.next();

        System.out.print("Enter Name: ");
        String name = scanner.next();

        System.out.print("Enter Track: ");
        String track = scanner.next();

        System.out.print("Enter Course Type: ");
        String type = scanner.next();

        Participant p = new RegularParticipant(id, name, track, type);
        manager.addParticipant(p);
    }

    static void addCorporateParticipant() {

        System.out.print("Enter ID: ");
        String id = scanner.next();

        System.out.print("Enter Name: ");
        String name = scanner.next();

        System.out.print("Enter Track: ");
        String track = scanner.next();

        System.out.print("Enter Company Name: ");
        String company = scanner.next();

        Participant p = new CorporateParticipant(id, name, track, company);
        manager.addParticipant(p);
    }

    static void addToWaiting() {

        System.out.print("Enter Participant ID: ");
        String id = scanner.next();

        for (Participant p : manager.participantList) {
            if (p.getParticipantId().equals(id)) {
                manager.addToWaitingList(p);
                return;
            }
        }

        System.out.println("Participant not found");
    }
}