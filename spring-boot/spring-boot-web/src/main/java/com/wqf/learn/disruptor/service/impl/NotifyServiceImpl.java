package com.wqf.learn.disruptor.service.impl;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.wqf.learn.disruptor.NotifyEvent;
import com.wqf.learn.disruptor.NotifyEventFactory;
import com.wqf.learn.disruptor.NotifyEventHandler;
import com.wqf.learn.disruptor.NotifyEventHandlerException;
import com.wqf.learn.disruptor.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

/**
 * @Descrption 业务接口
 * @Author wangqingfan
 * @Date 2019-06-28
 * @Version 1.0
 **/
@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService, DisposableBean, InitializingBean {

    private Disruptor<NotifyEvent> disruptor;
    private static final int RING_BUFFER_SIZE = 1024 * 1024;

    @Override
    public void destroy() throws Exception {
        log.info("------disruptor---单consumer---destroy----");
        disruptor.shutdown();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("------disruptor---单consumer---afterPropertiesSet----");
        disruptor = new Disruptor<NotifyEvent>(new NotifyEventFactory(), RING_BUFFER_SIZE, Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new NotifyEventHandlerException());
        disruptor.handleEventsWith(new NotifyEventHandler());
        disruptor.start();
    }

    @Override
    public void sendNotify(String message) {
        RingBuffer<NotifyEvent> ringBuffer = disruptor.getRingBuffer();
//        ringBuffer.publishEvent(new EventTranslatorOneArg<NotifyEvent,  String>() {
//            @Override
//            public void translateTo(NotifyEvent event, long sequence, String data) {
//                event.setMessgae(data);
//            }
//        }, message);
        ringBuffer.publishEvent((event, sequence, data) -> event.setMessgae(data), message);
    }
}
