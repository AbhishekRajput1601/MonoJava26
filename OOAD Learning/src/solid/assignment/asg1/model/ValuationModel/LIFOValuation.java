package solid.assignment.asg1.model.ValuationModel;

import solid.assignment.asg1.model.modelservice.Product;

import java.util.List;

public class LIFOValuation implements ValuationStrategy {
    public double calculateValue(List<Product> products) {
        double total = 0;
        for (int i = products.size() - 1; i >= 0; i--) {
            Product p = products.get(i);
            total += p.getQuantity() * p.getPrice();
        }
        return total;
    }
}
