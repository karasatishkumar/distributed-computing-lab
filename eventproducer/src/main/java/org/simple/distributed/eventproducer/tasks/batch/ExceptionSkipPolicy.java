package org.simple.distributed.eventproducer.tasks.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionSkipPolicy implements SkipPolicy {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionSkipPolicy.class);

	@Override
	public boolean shouldSkip(Throwable e, int arg1) throws SkipLimitExceededException {
		if (e != null && e instanceof FlatFileParseException) {
			logger.warn("Skipping........Record : Line - [{}] , Input - [{}]",
					((FlatFileParseException) e).getLineNumber(), ((FlatFileParseException) e).getInput());
			return true;
		}
		return false;
	}

}
