package com.krishnkant.websocket.controller;

import com.krishnkant.websocket.model.ChatMessage;
import com.krishnkant.websocket.util.WebSocketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate; // Kafka use karne ke liye
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    // Industry Practice: Direct message bhejne ke bajaye Kafka me event push karna
    @Autowired
    private KafkaTemplate<String, ChatMessage> kafkaTemplate;

    private static final String KAFKA_TOPIC = "chat-topic";

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        // Session se username nikalna
        String username = (String) Objects.requireNonNull(headerAccessor.getSessionAttributes())
                .get(WebSocketConstants.SESSION_USERNAME);

        if (username != null) {
            logger.info("User Disconnected: {}", username);

            // 1. LEAVE message object banaya
            ChatMessage chatMessage = new ChatMessage(ChatMessage.MessageType.LEAVE, null, username);

            /* * INDUSTRY CHANGE:
             * Pehle: messagingTemplate.convertAndSend(...) -> Direct broadcast
             * Ab: kafkaTemplate.send(...) -> Kafka me event bhejna
             * * Iska logic ye hai ki Consumer (Listener) is event ko pakdega
             * aur wahi se sabko broadcast karega. Single source of truth!
             */
            kafkaTemplate.send(KAFKA_TOPIC, chatMessage);
        }
    }
}