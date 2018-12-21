package org.simple.distributed.eventproducer.tasks.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableBatchProcessing
public class SystemLogBatchConfiguration {

	/*-
	 * TODO : All are hard coded.
	 * 	 	  Make things configurable and extendible.
	 */
	private Logger logger = LoggerFactory.getLogger(SystemLogBatchConfiguration.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SimpleJobLauncher simpleJobLauncher;

	@Scheduled(cron = "5 * * * * *")
	public void readAndSendSystemLog() {
		logger.info("Starting readAndSendSystemLog");
		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		try {
			JobExecution execution = simpleJobLauncher.run(
					readSystemFileJob(new JobCompletionNotificationListener(), step1(new ConsoleItemWriter<SystemLog>())),
					param);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
		logger.info("Finished readAndSendSystemLog");
	}

	@Bean
	public SkipPolicy exceptionSkipper() {
		return new ExceptionSkipPolicy();
	}

	@Bean
	public FlatFileItemReader<SystemLog> reader() {

		return new FlatFileItemReaderBuilder<SystemLog>().name("syslogReader")
				.resource(new FileSystemResource("/var/log/system.log")).fixedLength()
				.columns(new Range[] { new Range(1, 3), new Range(5, 6), new Range(8, 15), new Range(17, 28),
						new Range(30, Integer.MAX_VALUE) })
				.names(new String[] { "month", "day", "time", "machine", "message" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<SystemLog>() {
					{
						setTargetType(SystemLog.class);
					}
				}).build();
	}

	@Bean
	public ConsoleItemWriter<SystemLog> writer() {
		return new ConsoleItemWriter<SystemLog>();
	}

	@Bean
	public Job readSystemFileJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("readSystemFileJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
				.end().build();
	}

	@Bean
	public Step step1(ConsoleItemWriter<SystemLog> writer) {
		return stepBuilderFactory.get("step1").<SystemLog, SystemLog>chunk(10).reader(reader()).faultTolerant()
				.skipPolicy(exceptionSkipper()).writer(writer).build();
	}

}
