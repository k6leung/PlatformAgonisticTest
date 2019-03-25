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

@Profile("topicRabbitmq")
@Configuration
public class TopicRabbitMQConsumerConfiguration {

	@Autowired
	protected ConnectionFactory connectionFactory;
	
	@Autowired
	@Qualifier("subscribingQueue1")
	protected Queue subscribingQueue1;
	
	@Autowired
	@Qualifier("subscriberDeadLetterQueue1")
	protected Queue subscriberDeadLetterQueue1;
	
	@Autowired
	@Qualifier("subscribingQueue2")
	protected Queue subscribingQueue2;
	
	@Autowired
	@Qualifier("subscriberDeadLetterQueue2")
	protected Queue subscriberDeadLetterQueue2;
	
	@Autowired
	@Qualifier("subscribingQueue3")
	protected Queue subscribingQueue3;
	
	@Autowired
	@Qualifier("subscriberDeadLetterQueue3")
	protected Queue subscriberDeadLetterQueue3;
	
	public TopicRabbitMQConsumerConfiguration() {
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
		result.setQueues(this.subscribingQueue1, 
							this.subscriberDeadLetterQueue1, 
							this.subscribingQueue2, 
							this.subscriberDeadLetterQueue2, 
							this.subscribingQueue3,
							this.subscriberDeadLetterQueue3);
		
		return result;
	}
}
