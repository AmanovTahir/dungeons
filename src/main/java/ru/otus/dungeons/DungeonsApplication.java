package ru.otus.dungeons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Slf4j
@SpringBootApplication
@EnableWebSecurity
@EnableWebSocketMessageBroker
public class DungeonsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DungeonsApplication.class, args);
    }
}
