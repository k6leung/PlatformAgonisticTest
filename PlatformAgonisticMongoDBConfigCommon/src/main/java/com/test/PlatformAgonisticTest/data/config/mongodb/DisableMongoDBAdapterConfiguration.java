package com.test.PlatformAgonisticTest.data.config.mongodb;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

//the design relies on spring boot autoconfiguration; the logic is to disable the autoconfigurations when there is no mongo profile

@Profile({
	"!mongo & !localmongo"
})
@Configuration
@EnableAutoConfiguration(exclude={
	MongoAutoConfiguration.class,
	MongoReactiveAutoConfiguration.class
})
@EnableMongoRepositories(excludeFilters= {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MongoRepository.class),
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = PagingAndSortingRepository.class),
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = QueryByExampleExecutor.class),
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CrudRepository.class)
})

public class DisableMongoDBAdapterConfiguration {

	public DisableMongoDBAdapterConfiguration() {
		super();
	}
}
