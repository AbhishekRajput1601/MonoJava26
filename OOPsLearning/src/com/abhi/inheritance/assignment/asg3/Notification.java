package com.abhi.inheritance.assignment.asg3;

public class Notification {

    protected String recipient;
    protected String message;

    public Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    public void send() {
        System.out.println("Sending notification...");
    }

    public void logNotification() {
        System.out.println("Logging notification for: " + recipient);
    }
}
