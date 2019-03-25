package com.test.PlatformAgonisticTest.queue.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.PlatformAgonisticTest.data.model.TestData;
import com.test.PlatformAgonisticTest.database.adapter.common.CommonTestDataDatabaseAdapter;
import com.test.PlatformAgonisticTest.queue.consumer.service.common.CommonQueueConsumerService;

@Service
public class CommonQueueConsumerServiceImpl implements CommonQueueConsumerService {

	@Autowired
	protected CommonTestDataDatabaseAdapter testDataDatabaseAdapter;
	
	public CommonQueueConsumerServiceImpl() {
		super();
	}
	
	@Override
	public void consumeMessage(TestData message) {
		this.testDataDatabaseAdapter.persistTestData(message);
	}

}
