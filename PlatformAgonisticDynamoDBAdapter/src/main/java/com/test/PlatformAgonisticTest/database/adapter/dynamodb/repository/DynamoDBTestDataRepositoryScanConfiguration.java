package com.test.PlatformAgonisticTest.database.adapter.dynamodb.repository;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dynamo")
@Configuration
@EnableDynamoDBRepositories
public class DynamoDBTestDataRepositoryScanConfiguration {

	public DynamoDBTestDataRepositoryScanConfiguration() {
		super();
	}
}
