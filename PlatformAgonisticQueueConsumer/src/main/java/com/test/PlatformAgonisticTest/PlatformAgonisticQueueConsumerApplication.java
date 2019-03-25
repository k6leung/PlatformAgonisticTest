package com.test.PlatformAgonisticTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.PlatformAgonisticTest.queue.consumer.lambda.sqs.TestDataSQSEventConsumerFunction;
import com.test.PlatformAgonisticTest.queue.consumer.service.common.CommonQueueConsumerService;

@SpringBootApplication
public class PlatformAgonisticQueueConsumerApplication {

	@Autowired
	protected CommonQueueConsumerService queueConsumerService;
	
	@Autowired
	protected ObjectMapper mapper;
	
	public static void main(String[] args) {
		//SpringApplication.run(PlatformAgonisticRabbitMQConsumerApplication.class, args);
		SpringApplication application = new SpringApplication(PlatformAgonisticQueueConsumerApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		
		application.run(args);
	}
	
	@Bean
	@Profile("function")
	public TestDataSQSEventConsumerFunction testDataSQSEventConsumerFunction() {
		TestDataSQSEventConsumerFunction result = new TestDataSQSEventConsumerFunction();
		result.setMapper(this.mapper);
		result.setQueueConsumerService(this.queueConsumerService);
		
		return result;
	}

}
