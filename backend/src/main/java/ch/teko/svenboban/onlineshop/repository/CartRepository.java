package ch.teko.svenboban.onlineshop.repository;

import ch.teko.svenboban.onlineshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author sven.wetter@edu.teko.ch
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into CART(USER_ID, PRODUCT_ID, COUNT) values(:userId, :productId, :count)", nativeQuery = true)
    int saveAll(@Param("userId")int userId, @Param("productId")int productId, @Param("count")int count);

}
