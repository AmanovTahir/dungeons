package ru.otus.dungeons.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class WebSocketService {
    private static final String TOPIC = "/topic/receive";

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void send(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        String messageWithTimestamp = "[" + formattedDateTime + "] " + message;
        simpMessagingTemplate.convertAndSend(TOPIC, messageWithTimestamp);
    }
}
