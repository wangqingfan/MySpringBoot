package com.wqf.learn.druid;

import java.sql.SQLException;

import javax.sql.DataSource;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix="spring.datasource")
public class DuridConfig {
	
    private String url;  
      
    private String username;  
      
    private String password;  
      
    private String driverClassName;  
      
    private int initialSize;  
      
    private int minIdle;  
      
    private int maxActive;  
      
    private int maxWait;  
      
    private int timeBetweenEvictionRunsMillis;  
      
    private int minEvictableIdleTimeMillis;  
      
    private String validationQuery;  
      
    private boolean testWhileIdle;  
      
    private boolean testOnBorrow;  
      
    private boolean testOnReturn;  
      
    private boolean poolPreparedStatements;  
      
    private int maxPoolPreparedStatementPerConnectionSize;  
      
    private String filters;  
 
    private String connectionProperties;  
    
    @Bean
    private DataSource dataSource(){
    	DruidDataSource dataSource = new DruidDataSource();
    	dataSource.setUrl(url);
    	dataSource.setUsername(username);
    	dataSource.setPassword(password);
    	dataSource.setInitialSize(initialSize);
    	dataSource.setMinIdle(minIdle);
    	dataSource.setMaxActive(maxActive);
    	dataSource.setMaxWait(maxWait);
    	dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    	dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
    	dataSource.setValidationQuery(validationQuery);
    	dataSource.setTestWhileIdle(testWhileIdle);
    	dataSource.setTestOnBorrow(testOnBorrow);
    	dataSource.setTestOnReturn(testOnReturn);
    	dataSource.setPoolPreparedStatements(poolPreparedStatements);
    	dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
    	try {
			dataSource.setFilters(filters);
		} catch (SQLException e) {
		}
    	dataSource.setConnectionProperties(connectionProperties);
    	return dataSource;
    }
    
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
    	FilterRegistrationBean bean = new FilterRegistrationBean();
    	bean.addUrlPatterns("/*");
    	bean.setFilter(new WebStatFilter());
    	return bean;
    }
    
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
    	ServletRegistrationBean bean = new ServletRegistrationBean();
    	bean.addUrlMappings("/druid/*");
    	bean.setServlet(new StatViewServlet());
    	return bean;
    }
}
