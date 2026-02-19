package com.krishnkant.websocket.controller;

import com.krishnkant.websocket.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final KafkaProducerService producerService;
    private final KafkaTemplate<?, ?> kafkaTemplate;

    @Autowired
    public MessageController(KafkaProducerService producerService, KafkaTemplate<?, ?> kafkaTemplate) {
        this.producerService = producerService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String msg) {
        producerService.sendMessage(msg);
        return "Message sent to Kafka successfully!";
    }
//@GetMapping("/sendWithKey")
//public String sendWithKey(@RequestParam String key,
//                          @RequestParam String msg) {
//    producerService.sendMessage(key, msg);
//    return "Sent with key: " + key;
//}


}
