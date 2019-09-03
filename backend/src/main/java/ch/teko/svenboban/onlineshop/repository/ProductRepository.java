package ch.teko.svenboban.onlineshop.repository;

import ch.teko.svenboban.onlineshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sven.wetter@edu.teko.ch
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product getProductByName(String Name);
}
