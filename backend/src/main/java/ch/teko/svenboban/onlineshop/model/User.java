package ch.teko.svenboban.onlineshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author: Petrovic Boban
 **/
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String pid;
    private Long number;

    public User() {
        super();
    }

    public User(Long id, String pid, Long number) {
        super();
        this.id = id;
        this.pid = pid;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
