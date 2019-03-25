package com.test.PlatformAgonisticTest.topic.queue.adapter.config.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("topicRabbitmq")
@Configuration
public class RabbitMQAdapterTopicQueueConfiguration extends AbstractCloudConfig {

	@Value("${rabbitmq.service.name}")
	protected String rabbitmqServiceName;
	
	@Value("${rabbitmq.topic.queue.name1}")
	protected String queueName1;
	
	@Value("${rabbitmq.topic.queue.name2}")
	protected String queueName2;
	
	@Value("${rabbitmq.topic.queue.name3}")
	protected String queueName3;
	
	@Value("${rabbitmq.topic.exchange.name}")
	protected String topicExchangeName;
	
	@Value("${topic.sender.routing.key}")
	protected String routingKey;
	
	public RabbitMQAdapterTopicQueueConfiguration() {
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
	
	protected Queue createSubscribingQueue(String queueName, String deadLetterExchangeName, String deadLetterRoutingKey) {
		Map<String, Object> queueArguments = new HashMap<String, Object>();
		queueArguments.put("x-dead-letter-exchange", deadLetterExchangeName);
		queueArguments.put("x-dead-letter-routing-key", deadLetterRoutingKey);
		
		
		Queue result = new Queue(queueName, true, false, false, queueArguments);
		rabbitAdmin().declareQueue(result);
		result.setAdminsThatShouldDeclare(rabbitAdmin());
		
		return result;
	}
	
	protected Queue createDeadLetterQueue(String queueName) {
		Queue result = new Queue(queueName, true);
		rabbitAdmin().declareQueue(result);
		result.setAdminsThatShouldDeclare(rabbitAdmin());
		
		return result;
	}
	
	@Bean
	public Queue subscriberDeadLetterQueue1() {
		Queue result = this.createDeadLetterQueue("subscriber1DeadLetterQueue");
		
		return result;
	}
	
	@Bean
	public Queue subscribingQueue1() {
		Queue result = this.createSubscribingQueue(this.queueName1, "subscriber1DeadLetterExchange", "subscriber1DeadLetterQueue");
		
		return result;
	}
	
	@Bean
	public Queue subscriberDeadLetterQueue2() {
		Queue result = this.createDeadLetterQueue("subscriber2DeadLetterQueue");
		
		return result;
	}
	
	@Bean
	public Queue subscribingQueue2() {
		Queue result = this.createSubscribingQueue(this.queueName2, "subscriber2DeadLetterExchange", "subscriber2DeadLetterQueue");
		
		return result;
	}
	
	@Bean
	public Queue subscriberDeadLetterQueue3() {
		Queue result = this.createDeadLetterQueue("subscriber3DeadLetterQueue");
		
		return result;
	}
	
	@Bean
	public Queue subscribingQueue3() {
		Queue result = this.createSubscribingQueue(this.queueName3, "subscriber3DeadLetterExchange", "subscriber3DeadLetterQueue");
		
		return result;
	}
	
	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	protected DirectExchange createDirectExchange(String directExchangeName) {
		DirectExchange result = new DirectExchange(directExchangeName, true, true);
		rabbitAdmin().declareExchange(result);
		result.setAdminsThatShouldDeclare(rabbitAdmin());
		
		return result;
	}
	
	
	protected Binding createDirectQueueBinding(Queue queue, DirectExchange exchange) {
		Binding result = BindingBuilder.bind(queue).to(exchange).withQueueName();
		rabbitAdmin().declareBinding(result);
		result.setAdminsThatShouldDeclare(rabbitAdmin());
		
		return result;
	}
	
	@Bean
	public TopicExchange topicExchange() {
		TopicExchange result = new TopicExchange(this.topicExchangeName, true, true);
		rabbitAdmin().declareExchange(result);
		result.setAdminsThatShouldDeclare(rabbitAdmin());
		
		return result;
	}
	
	@Bean
	public DirectExchange subscriber1DeadLetterExchange() {
		DirectExchange result = this.createDirectExchange("subscriber1DeadLetterExchange");
		
		return result;
	}
	
	@Bean
	public DirectExchange subscriber2DeadLetterExchange() {
		DirectExchange result = this.createDirectExchange("subscriber2DeadLetterExchange");
		
		return result;
	}
	
	@Bean
	public DirectExchange subscriber3DeadLetterExchange() {
		DirectExchange result = this.createDirectExchange("subscriber3DeadLetterExchange");
		
		return result;
	}
	
	protected Binding topicQueueBinding(Queue queue) {
		Binding result = BindingBuilder.bind(queue).to(topicExchange()).with(this.routingKey);
		
		rabbitAdmin().declareBinding(result);
		result.setAdminsThatShouldDeclare(rabbitAdmin());
		
		return result;
	}
	
	@Bean
	public Binding topicQueueBinding1() {
		Binding result = this.topicQueueBinding(subscribingQueue1());
		
		return result;
	}
	
	@Bean
	public Binding deadLetterQueueBinding1() {
		Binding result = this.createDirectQueueBinding(subscriberDeadLetterQueue1(), subscriber1DeadLetterExchange());
		
		return result;
	}
	
	@Bean
	public Binding topicQueueBinding2() {
		Binding result = this.topicQueueBinding(subscribingQueue2());
		
		return result;
	}
	
	@Bean
	public Binding deadLetterQueueBinding2() {
		Binding result = this.createDirectQueueBinding(subscriberDeadLetterQueue2(), subscriber2DeadLetterExchange());
		
		return result;
	}
	
	@Bean
	public Binding topicQueueBinding3() {
		Binding result = this.topicQueueBinding(subscribingQueue3());
		
		return result;
	}
	
	@Bean
	public Binding deadLetterQueueBinding3() {
		Binding result = this.createDirectQueueBinding(subscriberDeadLetterQueue3(), subscriber3DeadLetterExchange());
		
		return result;
	}
	
	@Bean
	public RabbitTemplate amqpTemplate() {
		RabbitTemplate result = new RabbitTemplate(rabbitConnectionFactory());
		result.setMessageConverter(jsonMessageConverter());
		result.setExchange(this.topicExchangeName);
		result.setRoutingKey(this.routingKey);
		
		return result;
	}
}
