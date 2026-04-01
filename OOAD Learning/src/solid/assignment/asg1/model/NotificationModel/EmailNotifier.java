package solid.assignment.asg1.model.NotificationModel;

public class EmailNotifier implements Notifier {
    public void notify(String message) {
        System.out.println("[EMAIL] Notification sent: " + message);
    }
}
