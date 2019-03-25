package com.test.PlatformAgonisticTest.nonweb.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude= {WebMvcAutoConfiguration.class})
public class NonWebApplicationConfiguration {

	public NonWebApplicationConfiguration() {
		super();
	}
}
