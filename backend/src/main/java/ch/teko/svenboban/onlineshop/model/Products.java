package ch.teko.svenboban.onlineshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Products {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private double price;

    public int getId() {
        return id;
    }

    public Products setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Products setName(String name) {
        this.name = name;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Products setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public Products setLongDescription(String longDescription) {
        this.longDescription = longDescription;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Products setPrice(double price) {
        this.price = price;
        return this;
    }
}
