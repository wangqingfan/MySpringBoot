package com.wqf.learn.configuration;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {
	
	@SuppressWarnings(value = { "rawtypes","unchecked" })
	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory){
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(connectionFactory);
		
		GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(genericJackson2JsonRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);
		template.setValueSerializer(genericJackson2JsonRedisSerializer);
		return template;
	}
	
	
	//2.0版本以后自动springboot自带。早yml上添加配置即可，一下是自动手动话配置
	@Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisConnectionFactory connectionFactory) {
		//user信息缓存配置
	    RedisCacheConfiguration userCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(30)).disableCachingNullValues().prefixKeysWith("user");
	    //product信息缓存配置
	    RedisCacheConfiguration productCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)).disableCachingNullValues().prefixKeysWith("product");
	    Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
	    redisCacheConfigurationMap.put("user", userCacheConfiguration);
	    redisCacheConfigurationMap.put("product", productCacheConfiguration);
	    //初始化一个RedisCacheWriter
	    RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
	    
	    
	    //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
	    ClassLoader loader = this.getClass().getClassLoader();
	    JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer(loader);
	 //   GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
	    RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);
	    RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
	    
	    
	    //RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
	    //设置默认超过期时间是30秒
	    defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
	    //初始化RedisCacheManager
	    RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig, redisCacheConfigurationMap);
	    return cacheManager;
    }
}
