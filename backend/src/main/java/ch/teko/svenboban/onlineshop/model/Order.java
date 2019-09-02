package ch.teko.svenboban.onlineshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author sven.wetter@edu.teko.ch
 */
@Entity(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @Column (name = "ORDER_ID")
    private int orderId;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "PRODUCT_ID")
    private int productId;

    @Column (name = "COUNT")
    private int count;

    public int getCount() {
        return count;
    }

    public Order setCount(int count) {
        this.count = count;
        return this;
    }

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
