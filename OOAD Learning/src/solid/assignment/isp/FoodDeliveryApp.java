package solid.assignment.isp;

public class FoodDeliveryApp {
    public static void main(String[] args) {

        System.out.println("\n==================================");
        System.out.println("    FOOD DELIVERY PLATFORM");
        System.out.println("==================================");

        while (true) {
            System.out.println("\nSelect Role:");
            System.out.println("1.  Customer");
            System.out.println("2.  Restaurant");
            System.out.println("3.  Exit");

            int role = InputValidator.getChoice("\nEnter choice: ");

            switch (role) {
                case 1:
                    Customer customer = new Customer();

                    System.out.println("\n--- Customer Menu ---");
                    customer.placeOrder();
                    customer.trackOrder();
                    customer.rateDriver();
                    break;

                case 2:
                    Restaurant restaurant = new Restaurant();

                    System.out.println("\n--- Restaurant Panel ---");
                    restaurant.manageRestaurant();
                    break;

                case 3:
                    System.out.println("\n Exiting Platform...");
                    return;

                default:
                    System.out.println(" Invalid choice!");
            }
        }
    }
}
