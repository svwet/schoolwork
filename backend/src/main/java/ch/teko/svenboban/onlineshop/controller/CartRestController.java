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

    @RequestMapping(value="/addToCart", method=RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void saveCart(@RequestBody ArrayList<Cart> cart) {
        Integer valueCheck;
            try {
                for (int i = 0; i < cart.size(); i++) {
                    valueCheck = cartRepository.checkCart(cart.get(i).getUserId(), cart.get(i).getProductId());
                    if (valueCheck == null) {
                        cartRepository.saveAll(cart.get(i).getUserId(), cart.get(i).getProductId(), 1);
                    } else {
                        cartRepository.updateAdd(cart.get(i).getUserId(), cart.get(i).getProductId());
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
    }

    @RequestMapping(value = "/dropFromCart", method=RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void dropProductFromCart(@RequestBody ArrayList<Cart> cart) {
        Integer valueCheck;
        try {
            for (int i = 0; i < cart.size(); i++) {
                valueCheck = cartRepository.checkCart(cart.get(i).getUserId(), cart.get(i).getProductId());
                if (valueCheck == null) {
                    cartRepository.dropProductFromCart(cart.get(i).getUserId(), cart.get(i).getProductId(), 1);
                } else {
                    cartRepository.updateAdd(cart.get(i).getUserId(), cart.get(i).getProductId());
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}