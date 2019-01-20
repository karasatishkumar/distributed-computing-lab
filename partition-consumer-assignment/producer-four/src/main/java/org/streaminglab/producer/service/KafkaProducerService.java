package org.streaminglab.producer.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService implements ProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<Integer, String> simpleKafkaTemplate;

    @Override
    public void publish(String topic, Integer key, String message) {
        LOGGER.info("Message [{}] getting published to topic [{}] with key [{}]", message, topic, key);
        this.simpleKafkaTemplate.send(topic, key, message);
    }
}
