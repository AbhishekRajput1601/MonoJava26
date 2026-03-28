package com.abhi.inheritance.assignment.asg3;

public class EmailNotification extends Notification {

    public EmailNotification(String recipient, String message) {
        super(recipient, message);
    }

    @Override
    public void send() {
        super.logNotification();
        System.out.println("Email sent to " + recipient);
        System.out.println("Message: " + message);
        System.out.println("---------------------------");
    }
}
