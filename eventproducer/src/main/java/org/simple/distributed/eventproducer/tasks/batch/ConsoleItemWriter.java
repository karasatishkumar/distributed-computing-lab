package org.simple.distributed.eventproducer.tasks.batch;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.simple.distributed.eventproducer.tasks.kafka.IKafkaConstants;
import org.simple.distributed.eventproducer.tasks.kafka.KafkaProducerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsoleItemWriter<T> implements ItemWriter<T> {

	private static final Logger logger = LoggerFactory.getLogger(ConsoleItemWriter.class);

	@Autowired
	private KafkaProducerFactory kafkaProducerFactory;

	@Override
	public void write(List<? extends T> logs) throws Exception {

		Producer<Long, String> producer = kafkaProducerFactory.getKafkaProducer();
		if (logs != null && !logs.isEmpty()) {
			int index = 0;
			for (T log : logs) {
				ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME,
						"This is record " + index);

				try {
					RecordMetadata metadata = producer.send(record).get();
					logger.info("Record sent with key " + index + " to partition " + metadata.partition()
							+ " with offset " + metadata.offset());
				} catch (ExecutionException e) {
					logger.error("Error in sending record");
					logger.error(e.getMessage());
					e.printStackTrace();
				} catch (InterruptedException e) {
					logger.error("Error in sending record");
					logger.error(e.getMessage());
					e.printStackTrace();
				}
				index++;
			}
		}
	}

}
