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
            .setDescription("Apple MacBook Pro Silver (13.30\", Retina, Intel Core i5, 8GB, SSD)")
            .setPrice(1299.91);

    private static final Product PRODUCT_2 = new Product()
            .setId(2)
            .setName("HP Envy i9")
            .setDescription("HP Envy 13-ad080nz (13.30\", Full HD, Intel Core i7-7500U, 8GB, SSD)")
            .setPrice(899.95);

    private static final Product PRODUCT_3 = new Product()
            .setId(3)
            .setName("Lenovo P1")
            .setDescription("Das Lenovo ThinkPad P1 liefert als mobile Workstation erstklassige Leistung und ein Höchstmass an Zuverlässigkeit überall dort, wo Sie sie benötigen. Ob Sie aufwendige CAD-Modelle entwerfen, digitalen Content am Filmset kreieren oder Fertigungsprozesse an der Produktionsstätte analysieren - diese Workstation setzt Ihrer Produktivität dank Intel Xeon E Sechs-Kern-Prozessor, einer leistungsstarken Workstation-Grafikkarte und 16 GB DDR4-Arbeitsspeicher mit ECC-Fehlerkorrektur kaum Grenzen. Als Massenspeicher dient eine blitzschnelle SSD mit PCIe/NVMe-Anbindung. Ihre vertraulichen Daten wissen Sie dank zahlreichen Authentifizierungs- und Sicherheitsfunktionen wie TPM, Speicherverschlüsselung, Fingerabdruckscanner, Gesichtserkennung und Smart Card Reader auf der sicheren Seite")
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
