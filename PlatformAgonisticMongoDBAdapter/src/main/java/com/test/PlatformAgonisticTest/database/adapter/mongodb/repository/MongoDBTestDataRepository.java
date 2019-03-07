package com.test.PlatformAgonisticTest.database.adapter.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.PlatformAgonisticTest.data.model.TestData;

public interface MongoDBTestDataRepository extends MongoRepository<TestData, String> {

}
