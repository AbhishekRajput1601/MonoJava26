package model.notificationmodel;

public class EmailNotifier implements Notifier {
    public void notify(String message) {
        System.out.println("[EMAIL] " + message);
    }
}
