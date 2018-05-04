package com.wqf.learn.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class TaskExecutorConfig {

	@Bean
	public ThreadPoolTaskExecutor taskExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);//线程池维护线程的最少数量
		executor.setMaxPoolSize(10);//最大容量
		executor.setKeepAliveSeconds(200);//允许的空闲时间
		executor.setQueueCapacity(20);// 缓存队列
		executor.setRejectedExecutionHandler(null);//对拒绝task的处理策略.默认线程池对拒绝任务(无线程可用)的处理策略 ThreadPoolExecutor.CallerRunsPolicy策略 ,调用者的线程会执行该任务,如果执行器已关闭,则丢弃.
		return executor;
	}
}
