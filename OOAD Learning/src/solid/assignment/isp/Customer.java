package solid.assignment.isp;

public class Customer implements CustomerOperations {
    public void placeOrder() {
        System.out.println("Order placed");
    }

    public void trackOrder() {
        System.out.println("Tracking order...");
    }

    public void rateDriver() {
        System.out.println("Driver rated");
    }
}
