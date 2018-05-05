package com.wqf.learn.aop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class HelloAop {
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	public void aspect(){
		
	}
	
	@Before("aspect()")
	public void before(){
		log.info("<--------------Aop------------------->");
	}
}
