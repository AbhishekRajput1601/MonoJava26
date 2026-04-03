package model.modelservices;

import model.productmodel.Product;
import model.validationmodel.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

public class ProductService {

    public Optional<Product> findProduct(List<Product> products, String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
    }
    public Product getExistingProduct(List<Product> products, String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + name));
    }
}
