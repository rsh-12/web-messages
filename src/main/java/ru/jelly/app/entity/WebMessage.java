package ru.jelly.app.entity;
/*
 * Date: 1/26/21
 * Time: 8:14 PM
 * */

import javax.persistence.*;

@Entity
@Table(name = "message")
public class WebMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    public WebMessage() {
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WebMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
