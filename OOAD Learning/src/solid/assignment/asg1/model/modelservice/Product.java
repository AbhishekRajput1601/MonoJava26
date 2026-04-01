package solid.assignment.asg1.model.modelservice;

public class Product {
    private String name;
    private int quantity;
    private int reorderLevel;
    private double price;

    public Product(String name, int quantity, int reorderLevel, double price) {
        this.name = name;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getReorderLevel() {
        return reorderLevel;
    }
    public double getPrice() {
        return price;
    }

    public void addStock(int qty) {
        this.quantity += qty;
    }

    public void removeStock(int qty) {
        this.quantity -= qty;
    }

    @Override
    public String toString() {
        return "Product{name='" + this.name + "', quantity=" + this.quantity +
                ", reorderLevel=" + this.reorderLevel +
                ", price=" + this.price + "}";
    }
}
