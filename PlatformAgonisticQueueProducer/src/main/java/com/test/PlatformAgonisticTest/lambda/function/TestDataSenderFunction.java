package com.test.PlatformAgonisticTest.lambda.function;

import java.util.function.Function;

import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.PlatformAgonisticTest.data.model.TestData;
import com.test.PlatformAgonisticTest.queue.adapter.sender.common.CommonQueueSenderAdapter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestDataSenderFunction implements Function<TestData, String> {

	protected CommonQueueSenderAdapter sender;
	
	public TestDataSenderFunction() {
		super();
	}

	public void setSender(CommonQueueSenderAdapter sender) {
		this.sender = sender;
	}
	@Override
	public String apply(TestData message) {
		this.sender.sendMessage(message);
		
		return "DONE";
	}
	
}
