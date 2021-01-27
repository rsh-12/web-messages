package ru.jelly.app.task;
/*
 * Date: 1/27/21
 * Time: 8:02 AM
 * */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageScheduledTask {

    private static final Logger log = LoggerFactory.getLogger(MessageScheduledTask.class);

    @Scheduled(cron = "0 0/30 * * * *")
    public void deleteMessages() {
        log.info(">>> Deleting messages...");
    }
}
