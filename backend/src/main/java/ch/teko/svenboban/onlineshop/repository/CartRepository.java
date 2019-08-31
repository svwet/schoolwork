package ch.teko.svenboban.onlineshop.repository;

import ch.teko.svenboban.onlineshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sven.wetter@edu.teko.ch
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
