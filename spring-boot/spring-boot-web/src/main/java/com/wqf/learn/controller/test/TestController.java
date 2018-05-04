package com.wqf.learn.controller.test;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wqf.learn.configuration.MyTestConfig;
import com.wqf.learn.lombok.TestLombok;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

	@Resource
	private MyTestConfig myTestConfig;
	@Resource
	private TestLombok testLombok;
	
	@GetMapping
	public String test(){
		System.out.println("----成功-------");
		return "成功";
	}
	
	/**
	 * 测试自定义配置
	 * @return
	 */
	@GetMapping("/configuration")
	public MyTestConfig configuration(){
		return myTestConfig;
	}
	
	@GetMapping("/lombok")
	public TestLombok lombok(){
		log.info("----------进入lombok-----------");
	//	testLombok.setAge("13");
	//	testLombok.setName("aaa");
		TestLombok build = TestLombok.builder().age("25").name("王庆凡").build();
		log.info("----------进入lombok-----------"+build.toString());
		return build;
	}
	
	@PutMapping
	public TestLombok put(String age,String name){
		return TestLombok.builder().name(name).age(age).build();
	}
	
}
