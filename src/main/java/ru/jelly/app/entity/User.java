package ru.jelly.app.entity;
/*
 * Date: 1/28/21
 * Time: 7:38 AM
 * */

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at", updatable = false)
    private Date createdAt = new Date();

    @Column(name = "login_at")
    private Date loginAt = new Date();

    @OneToMany(mappedBy = "user")
    private List<WebMessage> messages;

    @PreRemove
    private void setNull() {
        messages.forEach(message -> message.setUser(null));
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public List<WebMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<WebMessage> messages) {
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
