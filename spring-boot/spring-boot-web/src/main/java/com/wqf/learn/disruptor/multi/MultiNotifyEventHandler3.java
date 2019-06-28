package com.wqf.learn.disruptor.multi;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.wqf.learn.disruptor.NotifyEvent;
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
public class MultiNotifyEventHandler3 implements EventHandler<NotifyEvent>, WorkHandler<NotifyEvent> {

    @Override
    public void onEvent(NotifyEvent notifyEvent) throws Exception {
        System.out.println("handler3--接收到消息");
        this.onEvent(notifyEvent);
    }

    @Override
    public void onEvent(NotifyEvent notifyEvent, long l, boolean b) throws Exception {
        System.out.println("handler3--"+notifyEvent.toString() + ">>>" + Thread.currentThread().toString());
        Thread.sleep(10*1000);
        notifyEvent.setMessgae("m3");
    }
}
