package ch.teko.svenboban.onlineshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author sven.wetter@edu.teko.ch
 */

@Entity
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private int productId;
    private String name;
    private String shortDescription;
    private String longDescription;
    private double price;

    public int getProductId() {
        return productId;
    }

    public Product setProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Product setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public Product setLongDescription(String longDescription) {
        this.longDescription = longDescription;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }
}
