package com.wqf.learn.redisqueue.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.wqf.learn.redisqueue.service.QueueWork;

@Slf4j
@Service
public class QueueWorkConsume implements QueueWork{
	
	@Override
	public String consume(String message) {
		log.info("------------消费业务逻辑-----------------消费消息："+message);
		return null;
	}
	
}
