package com.wqf.learn.disruptor.service.impl;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import com.wqf.learn.disruptor.NotifyEvent;
import com.wqf.learn.disruptor.NotifyEventFactory;
import com.wqf.learn.disruptor.NotifyEventHandler;
import com.wqf.learn.disruptor.NotifyEventHandlerException;
import com.wqf.learn.disruptor.multi.*;
import com.wqf.learn.disruptor.service.MultiNotifyService;
import com.wqf.learn.disruptor.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Descrption
 * @Author wangqingfan
 * @Date 2019-06-28
 * @Version 1.0
 **/
@Service
@Slf4j
public class MultiNotifyServiceImpl implements MultiNotifyService, DisposableBean, InitializingBean {

    private Disruptor<NotifyEvent> disruptor;
    private static final int RING_BUFFER_SIZE = 1024 * 1024;

    @Override
    public void destroy() throws Exception {
        log.info("------disruptor---多consumer---destroy----");
        disruptor.shutdown();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("------disruptor---多consumer---afterPropertiesSet----");
        //线程池数要大于等于handler数，一个线程，只会执行一个handler，
        // 但是disruptor认为传入线程池是一个过时方法，只要传入ThreadFactory就行
        //传入ThreadFactory，项目初始化创建等于handler的线程数，一个Thread只会进入一个hanlder，
        // 也就是每个handler尽量业务小，分解多，实现高吞吐
        //final ExecutorService executorService = Executors.newFixedThreadPool(6);
        //Mutil 就是Disruptor内部使用了cas ，SINGLE是直接set，对于咱们应用层不需要改变
        disruptor = new Disruptor<NotifyEvent>(new NotifyEventFactory(), RING_BUFFER_SIZE, Executors.defaultThreadFactory(), ProducerType.MULTI, new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new NotifyEventHandlerException());

        //handleEventsWith(new Handler1(),2);并行执行，.then后面传的是这两个执行完了在执行这个。
        final MultiNotifyEventHandler1 m1 = new MultiNotifyEventHandler1();
        final MultiNotifyEventHandler2 m2 = new MultiNotifyEventHandler2();
        final MultiNotifyEventHandler3 m3 = new MultiNotifyEventHandler3();
        final MultiNotifyEventHandler4 m4 = new MultiNotifyEventHandler4();
        final MultiNotifyEventHandler5 m5 = new MultiNotifyEventHandler5();
        final MultiNotifyEventHandler6 m6 = new MultiNotifyEventHandler6();
        //在这也可以实现多消费者。在总入口m1处，新增个并行即可
        //disruptor.handleEventsWith返回的是EventHandlerGroup对象，这个就是一个消费者组
        disruptor.handleEventsWith(m1);
        disruptor.after(m1).handleEventsWith(m2, m5);
        disruptor.after(m2).then(m4);
        disruptor.after(m5).then(m3);
        disruptor.after(m4, m3).then(m6);


//        EventHandlerGroup<NotifyEvent> ehg = disruptor.handleEventsWith(new MultiNotifyEventHandler1());
//        ehg.handleEventsWith(new MultiNotifyEventHandler2()).handleEventsWith(new MultiNotifyEventHandler3());
//        ehg.handleEventsWith(new MultiNotifyEventHandler4()).handleEventsWith(new MultiNotifyEventHandler5()).then(new MultiNotifyEventHandler6());;
        disruptor.start();
    }

    @Override
    public void sendMsg(String msg) {
        RingBuffer<NotifyEvent> ringBuffer = disruptor.getRingBuffer();
        //每次发送的sequence都是自增的
        ringBuffer.publishEvent((event, sequence, data) -> event.setMessgae(data), msg);
    }
}