package solid.assignment.asg1.model.ValuationModel;

import solid.assignment.asg1.model.modelservice.Product;

import java.util.List;

public class FIFOValuation implements ValuationStrategy {
    public double calculateValue(List<Product> products) {
        double total = 0;
        for (Product p : products) {
            total += p.getQuantity() * p.getPrice();
        }
        return total;
    }
}
