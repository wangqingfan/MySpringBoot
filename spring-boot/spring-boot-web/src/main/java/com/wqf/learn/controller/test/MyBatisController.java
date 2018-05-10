package com.wqf.learn.controller.test;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wqf.learn.dao.TestDao;

@RestController
@RequestMapping("/test/mybatis")
public class MyBatisController {

	@Resource
	private TestDao testDao;
	
	@GetMapping
	public Object findAdmin(){
		return testDao.findAllAdmins();
	}
	
	@GetMapping("/findBy")
	public Object findBy(@RequestParam("id")Integer id, @RequestParam("code") String code){
		return testDao.findAdminBy(id,code);
	}
	
	@GetMapping("/findById")
	public Object findById(Integer id){
		return testDao.findById(id);
	}
	
}
