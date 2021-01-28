package ru.jelly.app.task;
/*
 * Date: 1/27/21
 * Time: 8:02 AM
 * */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.jelly.app.entity.WebMessage;
import ru.jelly.app.repository.WebMessageRepository;

import java.util.List;

@Slf4j
@Component
public class MessageScheduledTask {

    private final WebMessageRepository messageRepository;

    @Autowired
    public MessageScheduledTask(WebMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // delete messages everey 30 minutes
    @Scheduled(cron = "0 0/30 * * * *")
    public void deleteMessages() {
        List<WebMessage> messages = messageRepository.oldMessages(10);

        log.info(">>> Deleting messages...");
        if (messages != null) messageRepository.deleteAll(messages);
    }
}
