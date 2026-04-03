package model.valuationmodel;

import model.productmodel.Product;

import java.util.List;
import java.util.stream.IntStream;

public class LIFOValuation implements ValuationStrategy {
    public double calculateValue(List<Product> products) {
        return IntStream.range(0, products.size())
                .mapToObj(i -> products.get(products.size() - 1 - i))
                .mapToDouble(p -> p.getQuantity() * p.getPrice())
                .sum();
    }
}
