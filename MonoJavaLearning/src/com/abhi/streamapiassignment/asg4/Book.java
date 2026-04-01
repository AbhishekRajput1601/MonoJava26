package com.abhi.streamapiassignment.asg4;

import java.io.Serializable;

public class Book implements Serializable {
    private int bookId;
    private String title;
    private String author;
    private String genre;
    private boolean available;
    private double price;

    public Book(int bookId, String title, String author, String genre, boolean available, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
        this.price = price;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return available; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "ID: " + bookId + ", Title: " + title +
                ", Author: " + author + ", Genre: " + genre +
                ", Available: " + available + ", Price: " + price;
    }
}
