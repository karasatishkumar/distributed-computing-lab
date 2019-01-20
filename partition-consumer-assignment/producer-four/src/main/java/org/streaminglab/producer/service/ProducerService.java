package org.streaminglab.producer.service;

public interface ProducerService {
    void publish(String topic, Integer key, String message);
}
