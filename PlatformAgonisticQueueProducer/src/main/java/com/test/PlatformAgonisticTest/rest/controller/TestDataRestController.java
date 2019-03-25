package com.test.PlatformAgonisticTest.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.PlatformAgonisticTest.data.model.TestData;
import com.test.PlatformAgonisticTest.queue.adapter.sender.common.CommonQueueSenderAdapter;

@RestController
@RequestMapping("/api")
public class TestDataRestController {
	
	@Autowired
	protected CommonQueueSenderAdapter sender;
	
	public TestDataRestController() {
		super();
	}
	
	@PostMapping(value="/testData", consumes = "application/json")
	public String sendTestData(@RequestBody TestData data) {
		this.sender.sendMessage(data);
		return "OK";
	}

}
