package com.test.PlatformAgonisticTest.queue.consumer.listener.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.test.PlatformAgonisticTest.data.model.TestData;
import com.test.PlatformAgonisticTest.queue.consumer.service.common.CommonQueueConsumerService;

@Profile("topicRabbitmq")
@Component
public class TopicRabbitMQListener1 {

	@Autowired
	protected CommonQueueConsumerService consummerService;
	
	public TopicRabbitMQListener1() {
		super();
	}

	@RabbitListener(queues="${rabbitmq.topic.queue.name1}", errorHandler="exceptionHandler")
	public void consumeMessage(TestData message) {
		String strVal = message.getStrVal();
		message.setStrVal(strVal + " - from subscriber 1");
		
		this.consummerService.consumeMessage(message);
		throw new RuntimeException("Test fail from topic subscriber 1 listener");
	}
}
