package org.streaminglab.producer.scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.streaminglab.producer.service.ProducerService;

@Component
public class MessageGenerator {

    @Value("${spring.kafka.topic.testTopic}")
    private String topic;

    @Value("${spring.kafka.topic.key}")
    private int key;

    @Autowired
    private ProducerService producerService;

    @Scheduled(fixedRate = 10000, initialDelay = 60000)
    public void generateMessage() {
        producerService.publish(topic, key, "Message Produced by producer-two");
    }
}
