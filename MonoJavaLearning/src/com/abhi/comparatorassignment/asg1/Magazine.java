package com.abhi.comparatorassignment.asg1;

public class Magazine extends Book {
    private final int issueNumber;
    private final String publicationMonth;

    public Magazine(String bookID, String title, String author, int issueNumber, String publicationMonth) {
        super(bookID, title, author);
        this.issueNumber = issueNumber;
        this.publicationMonth = publicationMonth;
    }

    @Override
    public void getDetails() {
        System.out.println("Magazine: " + title + " | Issue: " + issueNumber + " | Month: " + publicationMonth);
    }
}
