package com.wqf.learn.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wqf.learn.configuration.MyTestConfig;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestApplication {
	
	@Resource
	private MyTestConfig myTestConfig;
	
	@Test
	public void test(){
		System.out.println(myTestConfig.toString());
	}
}
