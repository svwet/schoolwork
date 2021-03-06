package ch.teko.svenboban.onlineshop.repository;

import ch.teko.svenboban.onlineshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author sven.wetter@edu.teko.ch
 */

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from CART where USER_ID =:userId and PRODUCT_ID =:productId", nativeQuery = true)
    void dropProductFromCart(@Param("userId")int userId, @Param("productId")int productId);

    @Transactional
    void removeByUserId(int userId);

    @Modifying
    @Transactional
    @Query(value = "update CART set count = count + 1 where USER_ID =:userId and PRODUCT_ID =:productId", nativeQuery = true)
    int updateAdd(@Param("userId")int userId, @Param("productId")int productId);

    @Transactional
    @Query(value = "select count from CART WHERE USER_ID =:userId and PRODUCT_ID=:productId", nativeQuery = true)
    Integer checkCart(@Param("userId")int userId, @Param("productId")int productId);

    @Modifying
    @Transactional
    @Query(value = "insert into ORDERS(USER_ID, PRODUCT_ID, COUNT) values(:userId, :productId, :count)", nativeQuery = true)
    int checkout(@Param("userId")int userId, @Param("productId")int productId, @Param("count")int count);

    List<Cart> getAllByUserId(int userId);

    List<Cart> findAllByUserId(int userId);

}
