package org.simple.distributed.eventproducer.tasks.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsoleItemWriter<T> implements ItemWriter<T> {

	private static final Logger logger = LoggerFactory.getLogger(ConsoleItemWriter.class);

	@Override
	public void write(List<? extends T> logs) throws Exception {

		if (logs != null && !logs.isEmpty()) {
			for (T log : logs) {
				logger.info("Message - [{}] ", log);
			}
		}
	}

}
