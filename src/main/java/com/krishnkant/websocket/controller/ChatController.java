package com.krishnkant.websocket.controller;

import com.krishnkant.websocket.model.ChatMessage;
import com.krishnkant.websocket.util.WebSocketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import java.util.Objects;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @MessageMapping("/chat.sendMessage")
    @SendTo(WebSocketConstants.TOPIC_PUBLIC)
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo(WebSocketConstants.TOPIC_PUBLIC)
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {

        // Industry Practice: Null Check lagana zaroori hai
        Objects.requireNonNull(headerAccessor.getSessionAttributes())
                .put(WebSocketConstants.SESSION_USERNAME, chatMessage.getSender());

        logger.info("User joined: {}", chatMessage.getSender()); // Proper Logging
        return chatMessage;
    }
}
