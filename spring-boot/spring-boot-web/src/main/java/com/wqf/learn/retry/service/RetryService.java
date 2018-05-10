package com.wqf.learn.retry.service;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryService {
	
	//zai Application上加@EnableRetry
	@Retryable(maxAttempts=3)
	public void retry(){
		System.out.println("-----------------retry------------");
		System.out.println(""+1/0);
	}
	
	
}
