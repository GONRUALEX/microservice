package com.microservice.curso.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class CreateTopic {

	@Bean
	public NewTopic createNewTopic() {
		Map<String, String> configurations = new HashMap<>();
		configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT); // delete borra mensaje, compact mantienen el actual
		configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // cuanto dura el mensaje dentro de kafka, 1 día puesto,, defecto -1 no se borrarán
		configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1002000200"); // MÁXIMO tamaño segmento, DEFETO 1 GB
		configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "10002022"); //MÁXIMO TAMAÑO DE MENSAJE, 1MB DEFECTO
	
		return TopicBuilder.name("topicazo").
				partitions(2).
				replicas(1).
				configs(configurations).build();
	}
}
