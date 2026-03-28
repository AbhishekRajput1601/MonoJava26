package com.abhi.comparatorassignment.asg1;

import java.util.Comparator;

public class IDComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getBookID().compareTo(b2.getBookID());
    }
}
