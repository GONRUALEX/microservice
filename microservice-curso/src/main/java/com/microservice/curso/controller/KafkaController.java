package com.microservice.curso.controller;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.curso.entities.KafkaMessage;
import com.microservice.curso.kafka.KafkaTopicService;



@RestController
@RequestMapping("/apikafka")
public class KafkaController {
	
	private Logger LOGGER = LoggerFactory.getLogger(KafkaListener.class);
	
    @Autowired
    private KafkaTopicService kafkaTopicService;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @GetMapping("/createtopic")
    public ResponseEntity<String> createTopic(@RequestParam String topicName) {
        try {
            kafkaTopicService.createTopicIfNotExists(topicName);
            return new ResponseEntity<String>("✅ Topic creado o ya existente: " + topicName, HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
        	return new ResponseEntity<String>("✅ Topic creado o ya existente: " + topicName, HttpStatus.OK);
        }
    }
    
    @PostMapping("/send-message-topic")
    public void sendMessageTopic(@RequestBody KafkaMessage kafkaMessage) {
    	String randomKey = generateRandomKey();
        kafkaTemplate.send(kafkaMessage.getTopic(), randomKey, kafkaMessage.getMessage());
        LOGGER.info("✅ Mensaje enviado a '{}' {}", kafkaMessage.getTopic(), kafkaMessage.getMessage());
    }
    
    private String generateRandomKey() {
        return "key-" + UUID.randomUUID();
    }

}
