package ru.jelly.app.entity;
/*
 * Date: 1/26/21
 * Time: 8:14 PM
 * */

//@Entity
//@Table(name = "message")
public class WebMessage {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String from;
    private String message;

    public WebMessage() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WebMessage{" +
                "from='" + from + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
