package solid.assignment.asg1.model.NotificationModel;

public class SMSNotifier implements Notifier {
    public void notify(String message) {
        System.out.println("[SMS] Notification sent: " + message);
    }
}
