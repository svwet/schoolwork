package ch.teko.svenboban.onlineshop.repository;

import ch.teko.svenboban.onlineshop.model.OnetimePassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Petrovic Boban
 **/
@Repository
public interface OnetimePasswordRepository extends JpaRepository<OnetimePassword, Long> {

}
