package com.test.PlatformAgonisticTest.database.adapter.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.test.PlatformAgonisticTest.data.model.TestData;
import com.test.PlatformAgonisticTest.database.adapter.common.CommonTestDataDatabaseAdapter;
import com.test.PlatformAgonisticTest.database.adapter.mongodb.repository.MongoDBTestDataRepository;

@Profile({
	"mongo | localmongo"
})
@Component
public class MongoDBTestDataDatabaseAdapterImpl implements CommonTestDataDatabaseAdapter {
	
	@Autowired
	protected MongoDBTestDataRepository mongoDBTestDataRepository;
	
	public MongoDBTestDataDatabaseAdapterImpl() {
		super();
	}

	@Override
	public TestData persistTestData(TestData testData) {
		TestData result = this.mongoDBTestDataRepository.save(testData);
		
		return result;
	}

	@Override
	public List<TestData> findAllTestData() {
		List<TestData> result = this.mongoDBTestDataRepository.findAll();
		
		return result;
	}

}
