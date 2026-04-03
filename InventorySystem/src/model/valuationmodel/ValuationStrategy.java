package model.valuationmodel;

import model.productmodel.Product;

import java.util.List;

public interface ValuationStrategy {
    double calculateValue(List<Product> products);
}
