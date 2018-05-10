package com.wqf.learn.cache;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wqf.learn.dao.TestDao;
import com.wqf.learn.domain.AdminInfo;

@Service
public class RedisCache {
	
	@Resource
	private TestDao testDao;
	
	@Cacheable(value="user",key="'user:'+#id",unless="#result==null")
	public AdminInfo findById(Integer id){
		return testDao.findById(id);
	}
}
