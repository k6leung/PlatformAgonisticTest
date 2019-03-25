package com.test.PlatformAgonisticTest.queue.adapter.sender.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.test.PlatformAgonisticTest.data.model.TestData;
import com.test.PlatformAgonisticTest.queue.adapter.sender.common.CommonQueueSenderAdapter;

@Profile(value={"directRabbitmq", "topicRabbitmq"})
@Component
public class RabbitMQSenderAdapterImpl implements CommonQueueSenderAdapter {
	
	@Autowired
	protected AmqpTemplate amqpTemplate;
	
	public RabbitMQSenderAdapterImpl() {
		super();
	}

	@Override
	public void sendMessage(TestData message) {
		this.amqpTemplate.convertAndSend(message);
	}

}
