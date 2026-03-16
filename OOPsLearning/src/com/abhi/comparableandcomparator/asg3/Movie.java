package com.abhi.comparableandcomparator.asg3;

public class Movie {

    String title;
    int year;

    Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String toString() {
        return title + " - " + year;
    }
}
