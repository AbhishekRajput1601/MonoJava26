package model.notificationmodel;

public class SMSNotifier implements Notifier {
    public void notify(String message) {
        System.out.println("[SMS] " + message);
    }
}
