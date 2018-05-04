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
		executor.setCorePoolSize(5);//�̳߳�ά���̵߳���������
		executor.setMaxPoolSize(10);//�������
		executor.setKeepAliveSeconds(200);//����Ŀ���ʱ��
		executor.setQueueCapacity(20);// �������
		executor.setRejectedExecutionHandler(null);//�Ծܾ�task�Ĵ������.Ĭ���̳߳ضԾܾ�����(���߳̿���)�Ĵ������ ThreadPoolExecutor.CallerRunsPolicy���� ,�����ߵ��̻߳�ִ�и�����,���ִ�����ѹر�,����.
		return executor;
	}
}
