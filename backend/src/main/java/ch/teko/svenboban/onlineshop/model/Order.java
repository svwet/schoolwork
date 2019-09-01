package ch.teko.svenboban.onlineshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author sven.wetter@edu.teko.ch
 */
@Entity
public class Order {
    @Id
    @GeneratedValue
    private int orderId;
    private int userId;
    private int productId;

    public int getOrderId() {
        return orderId;
    }

    public Order setOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Order setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getProductId() {
        return productId;
    }

    public Order setProductId(int productId) {
        this.productId = productId;
        return this;
    }
}
