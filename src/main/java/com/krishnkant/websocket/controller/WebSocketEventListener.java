package com.krishnkant.websocket.controller;

import com.krishnkant.websocket.model.ChatMessage;
import com.krishnkant.websocket.util.WebSocketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) Objects.requireNonNull(headerAccessor.getSessionAttributes())
                .get(WebSocketConstants.SESSION_USERNAME);

        if (username != null) {
            logger.info("User Disconnected: {}", username);

            ChatMessage chatMessage = new ChatMessage(ChatMessage.MessageType.LEAVE, null, username);

            messagingTemplate.convertAndSend(WebSocketConstants.TOPIC_PUBLIC, chatMessage);
        }
    }
}