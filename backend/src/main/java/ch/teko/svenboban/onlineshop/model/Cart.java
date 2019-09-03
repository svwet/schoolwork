package ch.teko.svenboban.onlineshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * @author sven.wetter@edu.teko.ch
 */
@Entity(name = "CART")
public class Cart {
    @Id
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "PRODUCT_ID")
    private int productId;

    @Column(name = "Count")
    private int count;

    public int getUserId() {
        return userId;
    }

    public Cart setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getProductId() {
        return productId;
    }

    public Cart setProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public int getCount() {
        return count;
    }

    public Cart setCount(int count) {
        this.count = count;
        return this;
    }
}
