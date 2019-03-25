package com.test.PlatformAgonisticTest.queue.adapter.sender.common;

import com.test.PlatformAgonisticTest.data.model.TestData;

public interface CommonQueueSenderAdapter {

	public void sendMessage(TestData message);
	
}
