package com.test.PlatformAgonisticTest.queue.adapter.sender.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.test.PlatformAgonisticTest.data.model.TestData;
import com.test.PlatformAgonisticTest.queue.adapter.sender.common.CommonQueueSenderAdapter;

@Profile("sqs")
@Component
public class SQSSenderAdapterImpl implements CommonQueueSenderAdapter {

	@Autowired
	protected QueueMessagingTemplate queueMessagingTemplate;
	
	public SQSSenderAdapterImpl() {
		super();
	}
	
	@Override
	public void sendMessage(TestData message) {
		this.queueMessagingTemplate.convertAndSend(message);
	}

}
