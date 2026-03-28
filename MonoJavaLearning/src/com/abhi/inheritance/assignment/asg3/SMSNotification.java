package com.abhi.inheritance.assignment.asg3;

public class SMSNotification extends Notification {

    public SMSNotification(String recipient, String message) {
        super(recipient, message);
    }

    @Override
    public void send() {
        super.logNotification();
        System.out.println("SMS sent to " + recipient);
        System.out.println("Message: " + message);
        System.out.println("---------------------------");
    }
}
