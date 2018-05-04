package com.wqf.learn.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="my-test")
@PropertySource("classpath:MyTest.properties")
public class MyTestConfig {
	
	private String name ;
	
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "MyTestConfig [name=" + name + ", age=" + age + "]";
	}
	
}
