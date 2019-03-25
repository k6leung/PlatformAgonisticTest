package com.test.PlatformAgonisticTest.queue.consumer.lambda.sqs;

import java.util.function.Function;

import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.PlatformAgonisticTest.data.model.TestData;
import com.test.PlatformAgonisticTest.queue.consumer.service.common.CommonQueueConsumerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestDataSQSEventConsumerFunction implements Function<Flux<SQSMessage>, Mono<String>> {

	protected CommonQueueConsumerService queueConsumerService;
	
	protected ObjectMapper mapper;

	public TestDataSQSEventConsumerFunction() {
		super();
	}
	
	public void setQueueConsumerService(CommonQueueConsumerService queueConsumerService) {
		this.queueConsumerService = queueConsumerService;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	
	protected TestData readData(String content) {
		TestData result = null;
		
		try {
			result = this.mapper.readValue(content, TestData.class);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		return result;
	}

	@Override
	public Mono<String> apply(Flux<SQSMessage> messages) {
		messages.subscribe(
				(message) -> {
					TestData record = this.readData(message.getBody());
					
					this.queueConsumerService.consumeMessage(record);
				}
		);
		
		return Mono.just("DONE");
	}
	
	
	
}
