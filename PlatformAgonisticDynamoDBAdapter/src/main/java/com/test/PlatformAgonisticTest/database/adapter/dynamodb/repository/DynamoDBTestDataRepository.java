package com.test.PlatformAgonisticTest.database.adapter.dynamodb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.test.PlatformAgonisticTest.data.model.TestData;

public interface DynamoDBTestDataRepository extends CrudRepository<TestData, String> {

	public List<TestData> findAll();
	
}
