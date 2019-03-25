package com.test.PlatformAgonisticTest.lambda.eventHandler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class SQSEventRequestHandler extends SpringBootRequestHandler<SQSEvent, String> {

	public SQSEventRequestHandler() {
		super();
	}
	
	@Override
	protected Object convertEvent(SQSEvent event) {
		Object result = event.getRecords();
		
		return result;
	}
	
}
