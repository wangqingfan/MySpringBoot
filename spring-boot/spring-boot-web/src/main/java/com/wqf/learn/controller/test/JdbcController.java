package com.wqf.learn.controller.test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jdbc")
public class JdbcController {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping
	public List<Map<String, Object>> getAdminInfo(){
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList("SELECT * FROM AdminInfo WHERE adminId <10");
		return queryForList;
	}
}
