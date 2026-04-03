package model.valuationmodel;

import model.productmodel.Product;

import java.util.List;

public class FIFOValuation implements ValuationStrategy {
    public double calculateValue(List<Product> products) {
        return products.stream()
                .mapToDouble(p -> p.getQuantity() * p.getPrice())
                .sum();
    }
}
