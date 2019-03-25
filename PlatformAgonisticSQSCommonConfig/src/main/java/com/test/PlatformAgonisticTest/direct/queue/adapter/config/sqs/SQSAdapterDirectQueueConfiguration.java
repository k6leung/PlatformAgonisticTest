package com.test.PlatformAgonisticTest.direct.queue.adapter.config.sqs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@Profile("sqs")
@Configuration
public class SQSAdapterDirectQueueConfiguration {

	@Value("${cloud.aws.credentials.accessKey}")
	protected String amazonAccessKey;
	
	@Value("${cloud.aws.credentials.secretKey}")
	protected String amazonSecretkey;
	
	@Value("${cloud.aws.region}")
	protected String region;
	
	public SQSAdapterDirectQueueConfiguration() {
		super();
	}
	
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(this.amazonAccessKey, this.amazonSecretkey);
	}
	
	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return new AWSStaticCredentialsProvider(this.amazonAWSCredentials());
	}
	
	@Bean
	public AmazonSQSAsync amazonSqsAsync() {
		return AmazonSQSAsyncClientBuilder.standard()
											.withCredentials(this.amazonAWSCredentialsProvider())
											.withRegion(this.region)
											.build();
	}
	
	@Bean
	public MessageConverter messageConverter() {
		MappingJackson2MessageConverter result = new MappingJackson2MessageConverter();
		ObjectMapper mapper = new ObjectMapper();
		result.setSerializedPayloadClass(String.class);
        result.setObjectMapper(mapper);
		
		
		return result;
	}
	
	@Bean
	public QueueMessagingTemplate queueMessagingTemplate() {
		QueueMessagingTemplate result =  new QueueMessagingTemplate(amazonSqsAsync(), (ResourceIdResolver)null, messageConverter());
		result.setDefaultDestinationName("TestQueue");
		
		return result;
	}
}
