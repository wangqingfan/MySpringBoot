package com.wqf.learn.controller.test;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wqf.learn.cache.RedisCache;
import com.wqf.learn.domain.AdminInfo;

@RestController
@RequestMapping("/test/cache")
public class CacheController {

	@Resource
	private RedisCache redisCache;
	
	@GetMapping
	public Object get(@RequestParam("id") Integer id){
		AdminInfo adminInfo = redisCache.findById(id);
		return adminInfo;
	}
}
