package ru.jelly.app.controller;
/*
 * Date: 1/26/21
 * Time: 8:32 PM
 * */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.jelly.app.entity.WebMessage;
import ru.jelly.app.service.WebMessageService;

import java.security.Principal;

@Slf4j
@Controller
@PreAuthorize("isAuthenticated()")
public class WebMessageController {

    private final WebMessageService webMessageService;

    @Autowired
    public WebMessageController(WebMessageService webMessageService) {
        this.webMessageService = webMessageService;
    }

    @GetMapping
    public String index(Principal principal, Model model) {

        if (principal != null) {
            model.addAttribute("username", principal.getName());
            model.addAttribute("messages", webMessageService.findAll());
        }

        return principal == null ? "security/login" : "chat";
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public WebMessage message(@Payload WebMessage message, Principal principal) {
        log.info(">>> message: {}", message);
        log.info(">>> username: {}", principal.getName());

        webMessageService.saveMessage(message, principal.getName());
        return message;
    }
}
