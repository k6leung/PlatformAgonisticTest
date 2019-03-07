package com.test.PlatformAgonisticTest.platform.function.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Profile;

@Profile("function")
@EnableAutoConfiguration(exclude= {WebMvcAutoConfiguration.class})
public class FunctionWebEnvironmentConfiguration {

	public FunctionWebEnvironmentConfiguration() {
		super();
	}
}
