package com.wqf.learn.disruptor.service;

/**
 * @Descrption 业务接口
 *
 * @Author wangqingfan
 * @Date 2019-06-28
 * @Version 1.0
 **/
public interface NotifyService {

    /**
     * 发送消息
     *
     * @param message
     */
    void sendNotify(String message);
}
