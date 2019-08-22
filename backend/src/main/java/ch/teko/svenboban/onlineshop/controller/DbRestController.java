package ch.teko.svenboban.onlineshop.controller;


import ch.teko.svenboban.onlineshop.repository.H2JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DbRestController {

    @Autowired
    private final H2JdbcRepository DataBase = new H2JdbcRepository();

    @GetMapping ("/products")
    public String products() {
        return DataBase.getProducts();
    }

    @GetMapping ("products/{id}")
    public String productsId(@PathVariable String id) {
        return DataBase.getProductsById(id);
    }

    @GetMapping ("/cart")
    public void cart() {
        DataBase.writeCartToDb();
    }

}