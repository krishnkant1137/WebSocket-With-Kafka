package com.krishnkant.websocket.service;

import com.krishnkant.websocket.model.ChatMessage;
import com.krishnkant.websocket.util.WebSocketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class ChatProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ChatProducerService.class);

    @Autowired
    private KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public void sendMessageToKafka(ChatMessage message) {
        // Industry Standard: CompletableFuture use karke check karna ki send successful hua ya nahi
        CompletableFuture<SendResult<String, ChatMessage>> future =
                kafkaTemplate.send(WebSocketConstants.KAFKA_TOPIC, message);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info("Message sent to Kafka successfully: offset [{}]",
                        result.getRecordMetadata().offset());
            } else {
                logger.error("Unable to send message to Kafka due to : {}", ex.getMessage());
                // Yahan aap retry logic ya database logging daal sakte hain
            }
        });
    }
}