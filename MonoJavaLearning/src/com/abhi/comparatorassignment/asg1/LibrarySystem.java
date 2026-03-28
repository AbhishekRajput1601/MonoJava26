package com.abhi.comparatorassignment.asg1;

import java.util.*;

public class LibrarySystem {

    private Set<Book> inventory = new HashSet<>();
    private Queue<IssueRequest> issueQueue = new LinkedList<>();


    public void addBook(Book book) {
        if (inventory.add(book)) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Duplicate book not allowed.");
        }
    }


    public void removeBook(String bookID) {
        Iterator<Book> iterator = inventory.iterator();
        while (iterator.hasNext()) {
            Book b = iterator.next();
            if (b.getBookID().equals(bookID)) {
                iterator.remove();
                System.out.println("Book removed successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }


    public void addIssueRequest(IssueRequest request) {
        issueQueue.offer(request);
        System.out.println("Request added successfully");
    }

    public void returnBook(String bookID){
        for (Book b : inventory) {
            if (b.getBookID().equals(bookID)) {
                b.returnBook();
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }


    public void processNextRequest() {
        if (issueQueue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        IssueRequest request = issueQueue.poll();
        for (Book b : inventory) {
            if (b.getBookID().equals(request.getBookID())) {
                if (b.isAvailable()) {
                    b.issueBook();
                    System.out.println("Book issued successfully.");
                } else {
                    System.out.println("Book not available.");
                }
                return;
            }
        }
        System.out.println("Book not found in inventory.");
    }


    public List<Book> getSortedByTitle() {
        List<Book> list = new ArrayList<>(inventory);
        list.sort(new TitleComparator());
        return list;
    }


    public List<Book> getSortedByID() {
        List<Book> list = new ArrayList<>(inventory);
        list.sort(new IDComparator());
        return list;
    }

    public void displayLibraryData() {
        if(inventory.isEmpty()){
            System.out.println("No book is available");
        }
        for (Book b : inventory) {
            b.getDetails();
            System.out.println("Available: " + b.isAvailable());
        }
    }


    public void displayAvailableBooks() {
        if(inventory.isEmpty()){
            System.out.println("No book is available");
        }
        for (Book b : inventory) {
            if (b.isAvailable()) {
                b.getDetails();
            }
        }
    }
}
