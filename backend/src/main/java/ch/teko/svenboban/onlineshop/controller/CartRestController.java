package ch.teko.svenboban.onlineshop.controller;

import ch.teko.svenboban.onlineshop.model.Cart;
import ch.teko.svenboban.onlineshop.repository.CartRepository;
import ch.teko.svenboban.onlineshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

/**
 * @author sven.wetter@edu.teko.ch
 */
@RestController
public class CartRestController {

    CartRepository cartRepository;

    @Autowired
    @Qualifier("RepoOrder")
    OrderRepository orderRepository;


    @Autowired
    UserController userController;

    @Autowired
    SmsController smsController;

    @Autowired
    public CartRestController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST, consumes = "application/json")
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/dropFromCart", method = RequestMethod.POST, consumes = "application/json")
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @GetMapping("/checkout")
    public void checkout() {
        Integer orderId = null;
        int user = userController.getCurrentUser();
        try {
            orderId = orderRepository.getOrderId();
        } catch (Exception e) {
            System.out.println(e);
        }

        if (orderId == null) {
            orderId = 1;
        } else {
            orderId = orderId+1;
        }

        try {
            List<Cart> cart = cartRepository.getAllByUserId(user);
            for (int i = 0; i < cart.size(); i++) {
                orderRepository.checkout(orderId, cart.get(i).getUserId(), cart.get(i).getProductId(), cart.get(i).getCount());
            }
            cartRepository.deleteAllByUserId(user);
        } catch (Exception e) {

        } finally {
            String destination = userController.getMobileByUserId(user);
            smsController.sendOrderSms(destination, "Thank you for your order. Your order with ID: " + orderId.toString() + " will be forwarded for approval");
        }
    }

}