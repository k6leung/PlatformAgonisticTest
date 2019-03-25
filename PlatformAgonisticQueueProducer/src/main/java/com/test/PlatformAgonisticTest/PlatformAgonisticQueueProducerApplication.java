package com.test.PlatformAgonisticTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.PlatformAgonisticTest.lambda.function.TestDataSenderFunction;
import com.test.PlatformAgonisticTest.queue.adapter.sender.common.CommonQueueSenderAdapter;

@SpringBootApplication
public class PlatformAgonisticQueueProducerApplication {

	@Autowired
	protected CommonQueueSenderAdapter queueSenderAdapter;
	
	@Autowired
	protected ObjectMapper objectMapper;
	
	public static void main(String[] args) {
		SpringApplication.run(PlatformAgonisticQueueProducerApplication.class, args);
	}
	
	@Bean
	@Profile("function")
	public TestDataSenderFunction testDataFunction() {
		TestDataSenderFunction result = new TestDataSenderFunction();
		result.setSender(this.queueSenderAdapter);
		
		return result;
	}

}
