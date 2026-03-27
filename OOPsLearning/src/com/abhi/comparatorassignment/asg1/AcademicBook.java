package com.abhi.comparatorassignment.asg1;

public class AcademicBook extends Book {
    private final String subject;
    private final String edition;

    public AcademicBook(String bookID, String title, String author, String subject, String edition) {
        super(bookID, title, author);
        this.subject = subject;
        this.edition = edition;
    }

    @Override
    public void getDetails() {
        System.out.println("Academic Book: " + title + " | Subject: " + subject + " | Edition: " + edition);
    }
}
