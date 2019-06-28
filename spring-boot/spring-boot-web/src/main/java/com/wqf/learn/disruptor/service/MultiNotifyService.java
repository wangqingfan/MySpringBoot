package com.wqf.learn.disruptor.service;

/**
 * @Descrption 多consumer消费
 *
 * @Author wangqingfan
 * @Date 2019-06-28
 * @Version 1.0
 **/

public interface MultiNotifyService {

    void sendMsg(String msg);
}
