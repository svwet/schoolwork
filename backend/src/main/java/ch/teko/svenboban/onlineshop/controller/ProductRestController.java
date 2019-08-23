package ch.teko.svenboban.onlineshop.controller;

import ch.teko.svenboban.onlineshop.model.Products;
import ch.teko.svenboban.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController {

    private final ProductRepository productRepository;
    @Autowired
    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping("/products")
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping ("/products/{id}")
    public Products getProductsById(@PathVariable int id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("There is no product with productId '%s'", id)));
    }

}
