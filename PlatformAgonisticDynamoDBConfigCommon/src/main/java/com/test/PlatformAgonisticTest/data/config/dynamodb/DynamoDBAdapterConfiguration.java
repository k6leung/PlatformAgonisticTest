package com.test.PlatformAgonisticTest.data.config.dynamodb;

import org.socialsignin.spring.data.dynamodb.mapping.DynamoDBMappingContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Profile("dynamo")
@Configuration
public class DynamoDBAdapterConfiguration {

	@Value("${amazon.aws.access.key}")
	protected String amazonAccessKey;
	
	@Value("${amazon.aws.secret.key}")
	protected String amazonSecretkey;
	
	@Value("${amazon.dynamodb.region}")
	protected String dynamoDbRegion;
	
	public DynamoDBAdapterConfiguration() {
		super();
	}
	
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(this.amazonAccessKey, this.amazonSecretkey);
	}
	
	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return new AWSStaticCredentialsProvider(this.amazonAWSCredentials());
	}
	
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder.standard()
											.withCredentials(this.amazonAWSCredentialsProvider())
											.withRegion(this.dynamoDbRegion).build();
	}
	
	@Bean
	public DynamoDBMappingContext dynamoDBMappingContext() {
		return new DynamoDBMappingContext();
	}
}
