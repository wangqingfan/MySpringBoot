package com.wqf.learn.configuration;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wqf.learn.filter.TestFilter;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean(){
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<Filter>();
		TestFilter filter = new TestFilter();
		bean.setFilter(filter);
		bean.addUrlPatterns("/*");
		return bean;
	}
}
