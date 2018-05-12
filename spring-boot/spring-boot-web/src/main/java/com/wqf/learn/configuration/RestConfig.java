package com.wqf.learn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * 类似httpclient ，测试在TestApplication中实现
 * @author win7
 *
 */
@Configuration
public class RestConfig {

	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(10000);
		factory.setConnectTimeout(10000);
		return restTemplate;
	}
}
