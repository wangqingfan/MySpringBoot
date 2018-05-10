package com.wqf.learn.redisqueue.service;

/**
 * 消费接口
 * @author win7
 *
 */
public interface QueueWork {

	String consume(String message);
}
