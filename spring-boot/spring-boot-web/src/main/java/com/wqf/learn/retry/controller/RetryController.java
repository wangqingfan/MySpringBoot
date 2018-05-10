package com.wqf.learn.retry.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wqf.learn.retry.service.RetryService;

@RestController
@RequestMapping("/test/retry")
public class RetryController {

	@Resource
	private RetryService retryService;
	
	@GetMapping
	public String get(){
		try {
			retryService.retry();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "success";
	}
}
