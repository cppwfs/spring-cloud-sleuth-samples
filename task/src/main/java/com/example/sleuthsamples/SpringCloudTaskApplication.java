package com.example.sleuthsamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
public class SpringCloudTaskApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringCloudTaskApplication.class);

	public static void main(String... args) {
		new SpringApplicationBuilder(SpringCloudTaskApplication.class).web(WebApplicationType.NONE).run(args);
	}

	@Bean
	CommandLineRunner myCommandLineRunner(Tracer tracer) {
		return args -> log.info("<ACCEPTANCE_TEST> <TRACE:{}> Hello from consumer", tracer.currentSpan().context().traceId());
	}

	@Bean
	ApplicationRunner myApplicationRunner(Tracer tracer) {
		return args -> log.info("<ACCEPTANCE_TEST> <TRACE:{}> Hello from producer", tracer.currentSpan().context().traceId());
	}

	@Bean
	TaskExecutionListener taskExecutionListener(Tracer tracer) {
		return new MyTaskExecutionListener(tracer);
	}
}