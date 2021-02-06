package ru.jelly.app.service.impl;
/*
 * Date: 1/27/21
 * Time: 8:23 AM
 * */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.jelly.app.entity.User;
import ru.jelly.app.entity.WebMessage;
import ru.jelly.app.repository.WebMessageRepository;
import ru.jelly.app.service.UserService;
import ru.jelly.app.service.WebMessageService;

import java.util.List;

@Slf4j
@Service
public class WebMessageServiceImpl implements WebMessageService {


    private final WebMessageRepository webMessageRepository;
    private final UserService userService;

    @Autowired
    public WebMessageServiceImpl(WebMessageRepository webMessageRepository, UserService userService) {
        this.webMessageRepository = webMessageRepository;
        this.userService = userService;
    }

    @Override
    public List<WebMessage> findAll() {
        List<WebMessage> webMessages = webMessageRepository.findAll(Sort.by("id").ascending());
        log.info("> get all messages: {}", webMessages.size());
        return webMessages;
    }

    @Override
    public void saveMessage(WebMessage message, String username) {
        log.info("> get the user by username: {}", username);
        User user = userService.findByUsername(username);

        log.info("> associate a message with a user {}", username);
        message.setUser(user);

        log.info("> save the message");
        webMessageRepository.save(message);
    }
}
