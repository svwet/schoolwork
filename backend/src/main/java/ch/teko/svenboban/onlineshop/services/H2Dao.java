package ch.teko.svenboban.onlineshop.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author sven.wetter@edu.teko.ch
 */

@Service
public class H2Dao {
    private static final Logger Log = LoggerFactory.getLogger(H2Dao.class);

    JdbcTemplate jdbcTemplate;

    @Autowired
    public H2Dao(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS "
                + "USERS(username VARCHAR(255) PRIMARY KEY, password VARCHAR(255), enabled BOOLEAN, mobile INTEGER);");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS "
                +"authorities(username VARCHAR(255), authority VARCHAR(255), foreign key (username) references users(username));");
    }
}
