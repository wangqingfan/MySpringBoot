package com.wqf.learn.controller.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * spring boot 2.0版本session共享，和1.x系列不一样
 * @author win7
 *
 */
@RestController
@RequestMapping("/redisHttpSession")
public class RedisHttpSessionController {

	@GetMapping
	public String get(HttpServletRequest request){
		String ceshi = (String)request.getSession().getAttribute("ceshi");
		return ceshi;
	}
	
	@GetMapping("/set")
	public String set(HttpServletRequest request){
		request.getSession().setAttribute("ceshi", "yes");
		return "success";
	}
		
	
}
