package com.microservice.curso.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
//crea topic dinamico
@Service
public class KafkaTopicService {

    @Autowired
    private AdminClient adminClient;

    // Método para obtener la configuración por defecto del topic
    private Map<String, String> getDefaultTopicConfig() {
        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT);
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // 1 día
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1002000200"); // Máximo tamaño segmento
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "10002022"); // Máximo tamaño mensaje
        return configurations;
    }

    // Método para crear un topic si no existe, aplicando la configuración por defecto
    public void createTopicIfNotExists(String topicName) throws InterruptedException, ExecutionException {
        ListTopicsResult topics = adminClient.listTopics();
        Set<String> existingTopics = topics.names().get();

        if (!existingTopics.contains(topicName)) {
            NewTopic topic = new NewTopic(topicName, 3, (short) 1) // 3 particiones, 1 réplica
                    .configs(getDefaultTopicConfig());

            adminClient.createTopics(Collections.singleton(topic));
            System.out.println("Topic creado: " + topicName);
        } else {
            System.out.println("El topic '" + topicName + "' ya existe.");
        }
    }
}
