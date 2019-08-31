package ch.teko.svenboban.onlineshop.controller;

import ch.teko.svenboban.onlineshop.model.Cart;
import ch.teko.svenboban.onlineshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

/**
 * @author sven.wetter@edu.teko.ch
 */
@RestController
public class CartRestController {

    private final CartRepository cartRepository;


    @Autowired
    public CartRestController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    @PostMapping("cart/save")
    public Cart saveCart(@Valid @RequestBody Cart cart ) {
        return cartRepository.save(cart);
    }

}
