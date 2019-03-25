package com.test.PlatformAgonisticTest.queue.consumer.listener.rabbitmq.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile(value= {"directRabbitmq", "topicRabbitmq"})
@Component("exceptionHandler")
public class ExceptionHandler implements RabbitListenerErrorHandler {
	
	private static Logger log = LoggerFactory.getLogger(ExceptionHandler.class);
	
	public ExceptionHandler() {
		super();
	}

	@Override
	public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message,
			ListenerExecutionFailedException exception) throws Exception {
		log.error(exception.getMessage(), exception);
		throw new AmqpRejectAndDontRequeueException(exception.getMessage(), exception);
		//return null;
	}

}
