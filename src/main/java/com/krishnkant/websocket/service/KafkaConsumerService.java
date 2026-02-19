package com.krishnkant.websocket.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final SimpMessagingTemplate messagingTemplate;

    public KafkaConsumerService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
//    @KafkaListener(topics = {"topicA", "topicB"}, groupId = "demo-group") // Kafka internally offset maintain karega:
    @KafkaListener(topics = "demo-topic", groupId = "demo-group")
    public void consume(String message) {
        System.out.println("Received from Kafka: " + message);

        // Push to WebSocket
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}

