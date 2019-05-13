package ch.teko.svenboban.onlineshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author: Petrovic Boban
 **/
@Entity
public class OnetimePassword {

    @Id
    @GeneratedValue
    private Long id;
    private String password;
    private Timestamp createdAt;

    public OnetimePassword() {
        super();
    }

    public OnetimePassword(Long id, String password, Timestamp createdAt) {
        super();
        this.id = id;
        this.password = password;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
