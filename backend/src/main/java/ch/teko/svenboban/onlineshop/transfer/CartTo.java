package ch.teko.svenboban.onlineshop.transfer;

import ch.teko.svenboban.onlineshop.model.Product;

/**
 * @author: Petrovic Boban
 **/
public class CartTo {
    private Product product;
    private int count;

    public Product getProduct() {
        return product;
    }

    public CartTo setProduct(Product product) {
        this.product = product;
        return this;
    }

    public int getCount() {
        return count;
    }

    public CartTo setCount(int count) {
        this.count = count;
        return this;
    }
}
