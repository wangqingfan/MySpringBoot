package com.wqf.learn.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @Descrption 消费者
 * @Author wangqingfan
 * @Date 2019-06-28
 * @Version 1.0
 **/
@Data
@Builder
@NoArgsConstructor
public class NotifyEventHandler implements EventHandler<NotifyEvent>, WorkHandler<NotifyEvent> {

    @Override
    public void onEvent(NotifyEvent notifyEvent) throws Exception {
        System.out.println("接收到消息");
        this.onEvent(notifyEvent);
    }

    @Override
    public void onEvent(NotifyEvent notifyEvent, long l, boolean b) throws Exception {
        System.out.println(notifyEvent + ">>>" + Thread.currentThread().toString());
    }
}
