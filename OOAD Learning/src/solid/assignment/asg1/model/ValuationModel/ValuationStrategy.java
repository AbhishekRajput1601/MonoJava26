package solid.assignment.asg1.model.ValuationModel;

import solid.assignment.asg1.model.modelservice.Product;

import java.util.List;

public interface ValuationStrategy {
    double calculateValue(List<Product> products);
}
