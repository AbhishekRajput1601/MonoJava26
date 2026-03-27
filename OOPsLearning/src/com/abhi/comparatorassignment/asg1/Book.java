package com.abhi.comparatorassignment.asg1;
import java.util.Objects;

public abstract class Book implements Comparable<Book> {
    protected String bookID;
    protected String title;
    protected String author;
    protected boolean isAvailable;

    public Book(String bookID, String title, String author) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void issueBook() {
        this.isAvailable = false;
    }

    public void returnBook() {
        this.isAvailable = true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(bookID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book other)) return false;
        return Objects.equals(this.bookID, other.bookID);
    }

    @Override
    public int compareTo(Book other) {
        return this.bookID.compareTo(other.bookID);
    }

    public abstract void getDetails();
}

