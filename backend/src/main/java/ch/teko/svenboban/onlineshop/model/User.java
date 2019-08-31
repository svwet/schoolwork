package ch.teko.svenboban.onlineshop.model;

import javax.persistence.*;

/**
 * @author: sven.wetter@edu.teko.ch
 **/
@Entity(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "MOBILE")
    private Long mobile;

    public int getUserId() {
        return userId;
    }

    public User setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String mail) {
        this.username = mail;
        return this;
    }

    public Long getMobile() {
        return mobile;
    }

    public User setMobile(Long mobile) {
        this.mobile = mobile;
        return this;
    }
}

