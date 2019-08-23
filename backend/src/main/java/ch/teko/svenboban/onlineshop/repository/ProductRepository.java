package ch.teko.svenboban.onlineshop.repository;

import ch.teko.svenboban.onlineshop.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

}
