package org.simple.distributed.eventproducer.tasks.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerFactory {

	private static Producer<Long, String> kafkaProducer;

	static {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		// props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,
		// CustomPartitioner.class.getName());
		kafkaProducer = new KafkaProducer<>(props);
	}

	public Producer<Long, String> getKafkaProducer() {
		return kafkaProducer;
	}
}
