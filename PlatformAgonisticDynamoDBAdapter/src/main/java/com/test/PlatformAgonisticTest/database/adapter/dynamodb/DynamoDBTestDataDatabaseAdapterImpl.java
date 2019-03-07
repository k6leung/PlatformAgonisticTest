package com.test.PlatformAgonisticTest.database.adapter.dynamodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.test.PlatformAgonisticTest.data.model.TestData;
import com.test.PlatformAgonisticTest.database.adapter.common.CommonTestDataDatabaseAdapter;
import com.test.PlatformAgonisticTest.database.adapter.dynamodb.repository.DynamoDBTestDataRepository;

@Profile("dynamo")
@Component
public class DynamoDBTestDataDatabaseAdapterImpl implements CommonTestDataDatabaseAdapter {

	@Autowired
	protected DynamoDBTestDataRepository dynamoDBTestDataRepository;
	
	public DynamoDBTestDataDatabaseAdapterImpl() {
		super();
	}
	
	@Override
	public TestData persistTestData(TestData testData) {
		TestData result = this.dynamoDBTestDataRepository.save(testData);
		
		return result;
	}

	@Override
	public List<TestData> findAllTestData() {
		List<TestData> result = this.dynamoDBTestDataRepository.findAll();
		
		return result;
	}

}
