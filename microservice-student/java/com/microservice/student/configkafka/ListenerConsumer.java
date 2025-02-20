package com.microservice.student.configkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class ListenerConsumer {

	private Logger LOGGER = LoggerFactory.getLogger(KafkaListener.class);
	
	@KafkaListener(topics= { "topicazo"}, groupId="grupo	1")
	public void listener(String message) {
		LOGGER.info	("Mensaje recibido: "+ message);
	}
}
