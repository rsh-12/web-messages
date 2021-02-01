package ru.jelly.app.entity;
/*
 * Date: 1/28/21
 * Time: 7:38 AM
 * */

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20, message = "Username must have between 3 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z]([._](?![._])|[a-zA-Z0-9]){3,20}",
            message = "Your username must start with a letter")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank
    @NotNull
    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "created_at", updatable = false)
    private Date createdAt = new Date();

    @Column(name = "login_at")
    private Date loginAt = new Date();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
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
        this.username = username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
