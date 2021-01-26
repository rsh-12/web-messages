package ru.jelly.app.controller;
/*
 * Date: 1/26/21
 * Time: 8:32 PM
 * */

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.jelly.app.entity.WebMessage;

@Controller
public class WebMessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public WebMessage message(WebMessage message) {
        System.out.println("message = " + message);
        return message;
    }
}
