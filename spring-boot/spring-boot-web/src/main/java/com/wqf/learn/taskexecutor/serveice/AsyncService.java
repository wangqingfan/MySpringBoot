package com.wqf.learn.taskexecutor.serveice;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@Async
@Slf4j
public class AsyncService {

	public void sayHello(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("<---Òì²½Ö´ÐÐsayHello--->");
	}
}
