package ch.teko.svenboban.onlineshop.repository;

import ch.teko.svenboban.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Petrovic Boban
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByPid(String pid);

}
