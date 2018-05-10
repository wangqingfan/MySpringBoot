package com.wqf.learn.redisqueue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RedisQueueProducter {
	
	@Resource
	private RedisTemplate<String, String> StringRedisTemplate;
	
	public void push(String queueName,String message){
		log.info("----队列名："+queueName+"------message:"+message);
		ListOperations<String, String> list = StringRedisTemplate.opsForList();
		list.leftPush(queueName, message);
		ValueOperations<String, String> value = StringRedisTemplate.opsForValue();
		value.set("queue", queueName);
	}
}
