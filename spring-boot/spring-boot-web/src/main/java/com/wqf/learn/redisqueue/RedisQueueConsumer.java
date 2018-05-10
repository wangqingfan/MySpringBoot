package com.wqf.learn.redisqueue;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.wqf.learn.redisqueue.service.QueueWork;

@Component
@Slf4j
@NoArgsConstructor
public class RedisQueueConsumer {

	@Resource
	private RedisTemplate<String, String> stringRedisTemplate;
	@Resource
	private QueueWork queueWork;
	
	@PostConstruct
	public void poll(){
		//这里只是模拟，因为@PostConstruct参数不允许有参数。正常设计，可以加一个成员变量queueName，客户指定queueName进行监听，也就是调用了带参构造器。new一个对象，也不是Spring管理的，在调用这个对象的queueName这个成员变量的值。因为是new所以不存在线程安全问题。
		ValueOperations<String, String> value  = stringRedisTemplate.opsForValue();
		String queueName = value.get("queue");
		ListOperations<String, String> list = stringRedisTemplate.opsForList();
		new Thread(() ->{
			while(true){
				String msg = list.rightPop(queueName,10,TimeUnit.SECONDS);//队列中 取不到值就阻塞10秒
				if(msg != null && !"".equals(msg)){
					queueWork.consume(msg);
				}
			}
		}).start();;
			
	}
}
