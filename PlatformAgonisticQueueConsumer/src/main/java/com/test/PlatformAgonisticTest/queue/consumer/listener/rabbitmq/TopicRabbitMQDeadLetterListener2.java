package com.test.PlatformAgonisticTest.queue.consumer.listener.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.test.PlatformAgonisticTest.data.model.TestData;
import com.test.PlatformAgonisticTest.queue.consumer.service.common.CommonQueueConsumerService;

@Profile("topicRabbitmq")
@Component
public class TopicRabbitMQDeadLetterListener2 {

	@Autowired
	protected CommonQueueConsumerService consummerService;
	
	public TopicRabbitMQDeadLetterListener2() {
		super();
	}
	
	@RabbitListener(queues="subscriber2DeadLetterQueue")
	public void consumeMessage(TestData message) {
		String strVal = message.getStrVal();
		message.setStrVal(strVal + " - from subscriber 2 dead letter listenner");
		
		this.consummerService.consumeMessage(message);
	}
}
