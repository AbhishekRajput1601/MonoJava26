package solid.assignment.asg1.model.modelservice;

public class ReorderService {
    public void reorder(Product product) {
        int reorderQty = 25;
        System.out.println("Reorder placed for " + reorderQty + " units of '" + product.getName() + "'");
        product.addStock(reorderQty);
    }
}
