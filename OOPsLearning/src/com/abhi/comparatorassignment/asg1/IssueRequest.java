package com.abhi.comparatorassignment.asg1;

import java.util.Date;

public class IssueRequest {
    private final String memberID;
    private final String bookID;
    private final Date requestDate;

    public IssueRequest(String memberID, String bookID) {
        this.memberID = memberID;
        this.bookID = bookID;
        this.requestDate = new Date();
    }

    public String getBookID() {
        return bookID;
    }

    public void getRequestInfo() {
        System.out.println("Member: " + memberID + " requested Book ID: " + bookID + " on " + requestDate);
    }
}