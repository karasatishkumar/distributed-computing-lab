package org.simple.distributed.eventconsumer.kafka;

import javax.annotation.PreDestroy;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerJob {

	private static Logger logger = LoggerFactory.getLogger(KafkaConsumerJob.class);

	@Autowired
	private KafkaConsumerFactory kafkaConsumerFactory;

	@Scheduled(cron = "2 * * * * *")
	public void printKafkaMessages() {
		logger.info("Running print Message Job [{}]", System.currentTimeMillis());
		Consumer<Long, String> consumer = kafkaConsumerFactory.getConsumer();
		ConsumerRecords<Long, String> records = consumer.poll(0);
		logger.info("Records size First - [{}] ", records.count());
		records.forEach(record -> {
			logger.info("Record Key - " + record.key());
			logger.info("Record value - " + record.value());
			logger.info("Record partition - " + record.partition());
			logger.info("Record offset - " + record.offset());
		});
		consumer.commitAsync();
	}

	@PreDestroy
	public void onDestroy() {
		logger.info("In Pre Destroy");
		kafkaConsumerFactory.getConsumer().close();
	}
}
