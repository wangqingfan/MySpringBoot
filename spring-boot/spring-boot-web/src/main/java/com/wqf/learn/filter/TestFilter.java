package com.wqf.learn.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestFilter implements Filter{

	public void destroy() {
		log.info("<---filter销毁--->");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String url = req.getRequestURI();
		log.info("-------url----"+url);
		/*Enumeration<String> parameterNames = req.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String parameterName = parameterNames.nextElement();
			log.info("-------parameterName----"+parameterName);
			String parameter = req.getParameter(parameterName);
			log.info("-------parameterValue----"+parameter);
		}*/
		Map<String, String[]> map = req.getParameterMap();
		log.info("-----doFilter-----map:"+map);
		map.forEach((k,v)->{
		    log.info("<---url:"+url+"---name:"+k+"---value:"+Arrays.toString(v)+"--->");
		});
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		log.info("<---filter初始化--->");
	}

}
