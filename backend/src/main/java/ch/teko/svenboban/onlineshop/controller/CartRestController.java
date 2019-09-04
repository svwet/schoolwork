package ch.teko.svenboban.onlineshop.controller;

import ch.teko.svenboban.onlineshop.model.Cart;
import ch.teko.svenboban.onlineshop.model.Product;
import ch.teko.svenboban.onlineshop.repository.CartRepository;
import ch.teko.svenboban.onlineshop.repository.OrderRepository;
import ch.teko.svenboban.onlineshop.repository.ProductRepository;
import ch.teko.svenboban.onlineshop.services.SmsSender;
import ch.teko.svenboban.onlineshop.services.UserService;
import ch.teko.svenboban.onlineshop.transfer.CartTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author sven.wetter@edu.teko.ch
 */
@RestController
@RequestMapping(value = "/cart")
public class CartRestController {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final SmsSender smsSender;

    @Autowired
    public CartRestController(CartRepository cartRepository, OrderRepository orderRepository, ProductRepository productRepository, UserService userService, SmsSender smsSender) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.smsSender = smsSender;
    }

    @GetMapping
    public List<CartTo> getCart() {
        List<CartTo> cartTos = new ArrayList<>();
        int currentUserId = userService.getCurrentUserId();
        List<Cart> cartByUserId = cartRepository.findAllByUserId(currentUserId);
        for (Cart cart : cartByUserId) {
            Product product = productRepository.findById(cart.getProductId()).orElse(null);
            int count = cart.getCount();
            cartTos.add(new CartTo().setCount(count).setProduct(product));
        }
        return cartTos;
    }

    @PostMapping
    @ResponseBody
    public Cart addToCart(@RequestBody Integer productId) {
        int currentUserId = userService.getCurrentUserId();
        boolean isProductAlreadyAdded = isProductAlreadyAdded(currentUserId, productId);
        if (isProductAlreadyAdded) {
            cartRepository.updateAdd(currentUserId, productId);
        } else {
            Cart cart = new Cart();
            cart.setProductId(productId);
            cart.setUserId(currentUserId);
            return cartRepository.save(cart);
        }
        return null;
    }

    @PostMapping("/dropProductFromCart")
    @ResponseBody
    public ResponseEntity dropProductFromCart(@RequestBody Integer productId) {
        int currentUserId = userService.getCurrentUserId();
        boolean isProductAlreadyAdded = isProductAlreadyAdded(currentUserId, productId);
        if (isProductAlreadyAdded) {
            cartRepository.dropProductFromCart(currentUserId, productId);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/checkout")
    public ResponseEntity<HttpStatus> checkout() {
        int currentUserId = userService.getCurrentUserId();
        Integer orderId = orderRepository.getOrderId();
        List<Cart> carts = cartRepository.getAllByUserId(currentUserId);
        if (orderId == null) {
            orderId = 1;
        } else {
            orderId = orderId+1;
        }
        for (Cart cart : carts) {
            orderRepository.checkout(orderId, cart.getUserId(), cart.getProductId(), cart.getCount());
        }
        cartRepository.removeByUserId(currentUserId);
        String message = "Thank you for your order. Your order with ID: " + orderId.toString() + " will be forwarded for approval.";
        smsSender.send(message);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private boolean isProductAlreadyAdded(int currentUserId, Integer productId) {
        Optional<Integer> valueCheck = Optional.ofNullable(cartRepository.checkCart(currentUserId, productId));
        return valueCheck.isPresent() && valueCheck.get() > 0;
    }
}
