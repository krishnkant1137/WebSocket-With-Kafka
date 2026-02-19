package com.krishnkant.websocket.controller;

import com.krishnkant.websocket.model.ChatMessage;
import com.krishnkant.websocket.service.ChatProducerService; // Nayi service use karenge
import com.krishnkant.websocket.util.WebSocketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import java.util.Objects;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatProducerService producerService; // Saara Kafka ka kaam ab ye handle karega

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        logger.info("Message received from user: {}", chatMessage.getSender());
        // Message ko seedha Service ko bhej diya
        producerService.sendMessageToKafka(chatMessage);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {

        // Session me username set karna (Standard Practice)
        Objects.requireNonNull(headerAccessor.getSessionAttributes())
                .put(WebSocketConstants.SESSION_USERNAME, chatMessage.getSender());

        logger.info("User added to session: {}", chatMessage.getSender());

        // Join event ko bhi Kafka me bhej diya
        producerService.sendMessageToKafka(chatMessage);
    }
}