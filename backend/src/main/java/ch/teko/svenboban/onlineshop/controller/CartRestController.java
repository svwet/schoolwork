package ch.teko.svenboban.onlineshop.controller;

import ch.teko.svenboban.onlineshop.model.Cart;
import ch.teko.svenboban.onlineshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

/**
 * @author sven.wetter@edu.teko.ch
 */
@RestController
public class CartRestController {

    CartRepository cartRepository;

    @Autowired
    public CartRestController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @RequestMapping(value="/posttesting", method=RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Cart saveCart(@RequestBody ArrayList<Cart> cart) {
    for(int i=0;i<cart.size();i++) {

        System.out.println("UserID:" + cart.get(i).getUserId()); //Debug Only
        System.out.println("ProductID:" + cart.get(i).getProductId()); //Debug Only
        System.out.println("Count:" + cart.get(i).getCount()); //Debug Only
        cartRepository.saveAll(cart.get(i).getUserId(), cart.get(i).getProductId(), cart.get(i).getCount());
    }
        return null;
    }

}
