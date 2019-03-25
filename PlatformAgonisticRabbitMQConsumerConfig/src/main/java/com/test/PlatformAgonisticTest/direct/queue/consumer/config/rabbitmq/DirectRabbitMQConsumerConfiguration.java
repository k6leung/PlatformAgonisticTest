package com.test.PlatformAgonisticTest.direct.queue.consumer.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Profile("directRabbitmq")
@Configuration
public class DirectRabbitMQConsumerConfiguration {
	
	@Autowired
	protected ConnectionFactory connectionFactory;
	
	@Autowired
	@Qualifier("messageQueue")
	protected Queue messageQueue;
	
	@Autowired
	@Qualifier("deadLetterQueue")
	protected Queue deadLetterQueue;
	
	public DirectRabbitMQConsumerConfiguration() {
		super();
	}
	
	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor result = new ThreadPoolTaskExecutor();
		result.setMaxPoolSize(20);
		
		return result;
	}
	
	@Bean
	public SimpleMessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer result = new SimpleMessageListenerContainer(this.connectionFactory);
		result.setTaskExecutor(taskExecutor());
		result.setQueues(this.messageQueue, this.deadLetterQueue);
		
		return result;
	}

}
