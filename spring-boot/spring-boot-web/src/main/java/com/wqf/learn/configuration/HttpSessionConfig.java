package com.wqf.learn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
//maxInactiveIntervalInSeconds为SpringSession的过期时间（单位：秒）
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)
public class HttpSessionConfig {
	
	@Bean  
    public static ConfigureRedisAction configureRedisAction() {  
        return ConfigureRedisAction.NO_OP;  
    }  
}
