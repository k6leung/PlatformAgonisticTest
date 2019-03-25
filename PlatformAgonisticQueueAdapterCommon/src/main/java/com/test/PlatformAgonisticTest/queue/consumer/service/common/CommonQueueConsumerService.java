package com.test.PlatformAgonisticTest.queue.consumer.service.common;

import com.test.PlatformAgonisticTest.data.model.TestData;

public interface CommonQueueConsumerService {

	public void consumeMessage(TestData message);
	
}
