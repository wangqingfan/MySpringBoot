package com.wqf.learn.taskexecutor.controller;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wqf.learn.taskexecutor.serveice.AsyncService;

@RestController
@RequestMapping("/test/task")
@Slf4j
public class TestTaskExecutorController {

	@Resource
	private TaskExecutor taskExecutor;
	@Resource
	private AsyncService asyncService;
	
	@GetMapping
	public String get(){
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.info("-------------异步执行----------");
			}
		});
		log.info("------------主方法执行----------");
		return "success";
	}
	
	@GetMapping("/sayHello")
	public String sayHello(){
		asyncService.sayHello();
		log.info("-------同步执行方法结束--------");
		return "hello";
	}
	
}
