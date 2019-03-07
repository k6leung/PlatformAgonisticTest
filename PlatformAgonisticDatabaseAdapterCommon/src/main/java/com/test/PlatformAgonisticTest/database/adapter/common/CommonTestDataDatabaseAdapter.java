package com.test.PlatformAgonisticTest.database.adapter.common;

import java.util.List;

import com.test.PlatformAgonisticTest.data.model.TestData;

public interface CommonTestDataDatabaseAdapter {

	public TestData persistTestData(TestData testData);
	
	public List<TestData> findAllTestData();
}
