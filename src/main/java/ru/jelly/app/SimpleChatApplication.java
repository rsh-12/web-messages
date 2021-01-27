package ru.jelly.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleChatApplication.class, args);
    }

}
