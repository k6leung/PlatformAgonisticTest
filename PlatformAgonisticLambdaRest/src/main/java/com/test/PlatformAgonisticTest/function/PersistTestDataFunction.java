package com.test.PlatformAgonisticTest.function;

import java.util.function.Function;

import com.test.PlatformAgonisticTest.data.model.TestData;
import com.test.PlatformAgonisticTest.database.adapter.common.CommonTestDataDatabaseAdapter;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

//performance optimization
//@Component("testData")
public class PersistTestDataFunction implements Function<TestData, TestData> {
	
	@Autowired
	protected CommonTestDataDatabaseAdapter testDatabaseAdapter;
	
	public PersistTestDataFunction() {
		super();
	}

	@Override
	public TestData apply(TestData testData) {
		TestData result = this.testDatabaseAdapter.persistTestData(testData);
		
		return result;
	}

}
