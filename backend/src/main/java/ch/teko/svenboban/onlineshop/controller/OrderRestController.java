package ch.teko.svenboban.onlineshop.controller;

import ch.teko.svenboban.onlineshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sven.wetter@edu.teko.ch
 */
@RestController
public class OrderRestController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderRestController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
