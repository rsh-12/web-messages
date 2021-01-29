package ru.jelly.app.controller;
/*
 * Date: 1/26/21
 * Time: 8:32 PM
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.jelly.app.entity.WebMessage;
import ru.jelly.app.repository.WebMessageRepository;

import java.security.Principal;

@Controller
public class WebMessageController {

    @Autowired
    private WebMessageRepository repository;

    @GetMapping
    public String index(Principal principal, Model model) {

        model.addAttribute("messages", repository.findAll(Sort.by("id").ascending()));
        return "index";
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public WebMessage message(@Payload WebMessage message) {
        System.out.println("message = " + message);
//        repository.save(message);
        return message;
    }
}
