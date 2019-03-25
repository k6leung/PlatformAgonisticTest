package com.test.PlatformAgonisticTest.queue.consumer.listener.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.test.PlatformAgonisticTest.data.model.TestData;
import com.test.PlatformAgonisticTest.queue.consumer.service.common.CommonQueueConsumerService;

@Profile("directRabbitmq")
@Component
public class DirectRabbitMQListener {
	
	@Autowired
	protected CommonQueueConsumerService consummerService;
	
	public DirectRabbitMQListener() {
		super();
	}

	@RabbitListener(queues="${rabbitmq.direct.queue.name}", errorHandler="exceptionHandler")
	public void consumeMessage(TestData message) {
		this.consummerService.consumeMessage(message);
		throw new RuntimeException("Test fail from direct queue listener");
	}
}
