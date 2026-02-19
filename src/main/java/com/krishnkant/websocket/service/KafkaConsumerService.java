package com.krishnkant.websocket.service;

import com.krishnkant.websocket.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Industry Standard: GroupId ko properties file se uthana chahiye,
     * par abhi hum hardcode kar rahe hain.
     */
    @KafkaListener(topics = "chat-topic", groupId = "chat-group")
    public void consume(ChatMessage chatMessage) {
        logger.info("Consumed message from Kafka: {} by {}",
                chatMessage.getType(), chatMessage.getSender());

        try {
            // Kafka se aya message ab real-time me sabhi clients ko broadcast hoga
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        } catch (Exception e) {
            logger.error("Error broadcasting message via WebSocket: ", e);
            // Industry Practice: Yahan aap 'Retry' ya 'Dead Letter Queue' ka logic laga sakte hain
        }
    }
}