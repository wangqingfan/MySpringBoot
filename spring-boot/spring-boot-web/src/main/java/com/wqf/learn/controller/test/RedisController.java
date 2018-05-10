package com.wqf.learn.controller.test;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wqf.learn.domain.AdminInfo;
import com.wqf.learn.redisqueue.RedisQueueProducter;

@RestController
@RequestMapping("/test/redis")
public class RedisController {

	@Resource
	private RedisTemplate<String,String> stringRedisTemplate;
	@Resource
	private RedisTemplate<String,Object> redisTemplate;
	@Resource
	private RedisQueueProducter producter;
	
	@GetMapping
	public String get(@RequestParam("string") String str){
		ValueOperations<String,String> value = stringRedisTemplate.opsForValue();
		value.set("test", str);
		return value.get("test");
	}
	
	@GetMapping("/pushMessage")
	public String pushMessage(@RequestParam("queue") String queue,@RequestParam("message")String message){
		producter.push(queue, message);
		return "success";
	}
	
	@GetMapping("/setDomain")
	public String setDomain(){
		AdminInfo adminInfo = new AdminInfo();
		adminInfo.setAdminCode("ceshi");
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		hash.put("ceshiDomain", "admin", adminInfo);
		return "success";
	}
	
	@GetMapping("/getDomain")
	public Object getDomain(){
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		return hash.get("ceshiDomain", "admin");
	}
}
