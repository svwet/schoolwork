package ch.teko.svenboban.onlineshop.repository;

import ch.teko.svenboban.onlineshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

/**
 * @author sven.wetter@edu.teko.ch
 */

@Repository("RepoOrder")
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into ORDERS(ORDER_ID, USER_ID, PRODUCT_ID, COUNT) values(:orderId, :userId, :productId, :count)", nativeQuery = true)
    int checkout(@Param("orderId")int orderId, @Param("userId")int userId, @Param("productId")int productId, @Param("count")int count);

    @Query(value = "SELECT MAX(ORDER_ID) FROM ORDERS", nativeQuery = true)
    Integer getOrderId();

}

