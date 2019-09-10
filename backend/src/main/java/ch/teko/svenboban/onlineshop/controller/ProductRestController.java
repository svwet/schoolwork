package ch.teko.svenboban.onlineshop.controller;

import ch.teko.svenboban.onlineshop.model.Product;
import ch.teko.svenboban.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author sven.wetter@edu.teko.ch
 */

@RestController
@RequestMapping(value = "api")
public class ProductRestController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping ("/products/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException(String.format("There is no product with productId '%s'", productId)));
    }

    @GetMapping ("/products/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productRepository.getProductByName(name);
    }
}
