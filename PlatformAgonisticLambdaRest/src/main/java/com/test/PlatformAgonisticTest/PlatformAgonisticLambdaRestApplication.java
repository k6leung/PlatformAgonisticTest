package com.test.PlatformAgonisticTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.test.PlatformAgonisticTest.config.FunctionConfiguration;
import com.test.PlatformAgonisticTest.data.config.dynamodb.DynamoDBAdapterConfiguration;
import com.test.PlatformAgonisticTest.data.config.mongodb.DisableMongoDBAdapterConfiguration;
import com.test.PlatformAgonisticTest.database.adapter.dynamodb.DynamoDBTestDataDatabaseAdapterImpl;
import com.test.PlatformAgonisticTest.database.adapter.dynamodb.repository.DynamoDBTestDataRepositoryScanConfiguration;
import com.test.PlatformAgonisticTest.database.adapter.mongodb.MongoDBTestDataDatabaseAdapterImpl;
import com.test.PlatformAgonisticTest.platform.container.config.ContainerWebEnvironmentConfiguration;
import com.test.PlatformAgonisticTest.platform.function.config.FunctionWebEnvironmentConfiguration;

@SpringBootApplication
@Import(value= {
		FunctionConfiguration.class,
		DynamoDBTestDataDatabaseAdapterImpl.class,
		DynamoDBTestDataRepositoryScanConfiguration.class,
		DynamoDBAdapterConfiguration.class,
		ContainerWebEnvironmentConfiguration.class,
		FunctionWebEnvironmentConfiguration.class,
		MongoDBTestDataDatabaseAdapterImpl.class,
		DisableMongoDBAdapterConfiguration.class
})
public class PlatformAgonisticLambdaRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformAgonisticLambdaRestApplication.class, args);
	}

}
