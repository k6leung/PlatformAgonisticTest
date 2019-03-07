package com.test.PlatformAgonisticTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.PlatformAgonisticTest.function.PersistTestDataFunction;

@Configuration
public class FunctionConfiguration {
	
	public FunctionConfiguration() {
		super();
	}
	
	@Bean
	public PersistTestDataFunction testData() {
		return new PersistTestDataFunction();
	}

}
