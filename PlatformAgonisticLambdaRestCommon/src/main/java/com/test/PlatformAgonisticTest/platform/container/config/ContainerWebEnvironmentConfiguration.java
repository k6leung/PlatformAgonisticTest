package com.test.PlatformAgonisticTest.platform.container.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Profile({
	"container",
	"standalone"
})
@Configuration
@Import({WebMvcAutoConfiguration.class})
public class ContainerWebEnvironmentConfiguration {

	public ContainerWebEnvironmentConfiguration() {
		super();
	}
}
