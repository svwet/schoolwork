package ch.teko.svenboban.onlineshop.controller;

import ch.teko.svenboban.onlineshop.model.Product;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Petrovic Boban
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProductRestController {

    private static final Product PRODUCT_1 = new Product()
            .setId(1)
            .setName("MacBook Pro i7")
            .setDescription("One of the best laptops ever")
            .setPrice(1299.91);

    private static final Product PRODUCT_2 = new Product()
            .setId(2)
            .setName("HP Envy i9")
            .setDescription("Best price ever")
            .setPrice(899.95);

    private static final Product PRODUCT_3 = new Product()
            .setId(3)
            .setName("Lenovo P1")
            .setDescription("Best laptop for businesses up to 20 people")
            .setPrice(2399.50);

    private static final List<Product> PRODUCTS = new ArrayList<>(Arrays.asList(PRODUCT_1, PRODUCT_2, PRODUCT_3));

    @GetMapping("/products")
    public List<Product> getProducts() {
        return PRODUCTS;
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return PRODUCTS.stream()
                       .filter(product -> productId == product.getId())
                       .findFirst()
                       .orElseThrow(() -> new IllegalArgumentException(String.format("There is no product with productId '%s'", productId)));
    }

}
