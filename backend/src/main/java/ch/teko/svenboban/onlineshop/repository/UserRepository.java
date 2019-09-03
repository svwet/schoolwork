package ch.teko.svenboban.onlineshop.repository;

import ch.teko.svenboban.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author sven.wetter@edu.teko.ch
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT userId FROM USER WHERE USERNAME = :name")
    int findIdByName(@Param("name")String name);

    @Query(value = "Select MOBILE FROM USER WHERE USER_ID = :userId", nativeQuery = true)
    String getMobileByUserId(int userId);

}
