package com.krishnkant.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate; //Spring ka helper class hai jo message send karta hai Kafka ko.
    }

    public void sendMessage(String message) {

        kafkaTemplate.send("demo-topic", message); //Ye message publish kar raha hai demo-topic me.
        System.out.println("Message sent to Kafka: " + message);
    }
//    public void sendMessage(String key, String message) {
//        kafkaTemplate.send("demo-topic", key, message);
//    }
}
