package com.test.PlatformAgonisticTest.direct.queue.adapter.config.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("directRabbitmq")
@Configuration
public class RabbitMQAdapterDirectQueueConfiguration extends AbstractCloudConfig {

	@Value("${rabbitmq.service.name}")
	protected String rabbitmqServiceName;
	
	@Value("${rabbitmq.direct.queue.name}")
	protected String queueName;
	
	@Value("${rabbitmq.direct.exchange.name}")
	protected String directExchangeName;
	
	public RabbitMQAdapterDirectQueueConfiguration() {
		super();
	}
	
	@Bean
	public ConnectionFactory rabbitConnectionFactory() {
		return connectionFactory().rabbitConnectionFactory(this.rabbitmqServiceName);
	}
	
	@Bean
	public RabbitAdmin rabbitAdmin() {
		return new RabbitAdmin(rabbitConnectionFactory());
	}
	
	@Bean
	public Queue deadLetterQueue() {
		Queue result = new Queue("deadLetter", true);
		rabbitAdmin().declareQueue(result);
		result.setAdminsThatShouldDeclare(rabbitAdmin());
		
		return result;
	}
	
	@Bean
	public Queue messageQueue() {
		Map<String, Object> queueArguments = new HashMap<String, Object>();
		queueArguments.put("x-dead-letter-exchange", "");
		queueArguments.put("x-dead-letter-routing-key", "deadLetter");
		
		Queue result = new Queue(this.queueName, true, false, false, queueArguments);
		rabbitAdmin().declareQueue(result);
		result.setAdminsThatShouldDeclare(rabbitAdmin());
		
		return result;
	}
	
	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public DirectExchange directExchange() {
		DirectExchange result = new DirectExchange(this.directExchangeName, true, true);
		rabbitAdmin().declareExchange(result);
		result.setAdminsThatShouldDeclare(rabbitAdmin());
		
		return result;
	}
	
	@Bean
	public Binding directQueueBinding() {
		Binding result = BindingBuilder.bind(messageQueue()).to(directExchange()).withQueueName();
		rabbitAdmin().declareBinding(result);
		result.setAdminsThatShouldDeclare(rabbitAdmin());
		
		return result;
	}
	
	@Bean
	public RabbitTemplate amqpTemplate() {
		RabbitTemplate result = new RabbitTemplate(rabbitConnectionFactory());
		result.setMessageConverter(jsonMessageConverter());
		result.setExchange(this.directExchangeName);
		result.setRoutingKey(this.queueName);
		
		return result;
	}
}
