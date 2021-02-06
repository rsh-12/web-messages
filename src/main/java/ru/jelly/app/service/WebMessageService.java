package ru.jelly.app.service;

import ru.jelly.app.entity.WebMessage;

import java.util.List;

public interface WebMessageService {

    List<WebMessage> findAll();

    void saveMessage(WebMessage message, String username);
}
