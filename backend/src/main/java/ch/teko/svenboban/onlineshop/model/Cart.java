package ch.teko.svenboban.onlineshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * @author sven.wetter@edu.teko.ch
 */
@Entity
public class Cart {
    @Id
    @GeneratedValue
    @Column(name = "CART_ID")
    private int cartId;
    private int userId;
    private int productId;
    private int count;

    public int getCartId() {
        return cartId;
    }

    public Cart setCartId(int cartId) {
        this.cartId = cartId;
        return this;
    }

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
