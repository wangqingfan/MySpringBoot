package com.wqf.learn.disruptor;

import com.lmax.disruptor.EventFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Descrption 生产数据工厂类
 *
 * @Author wangqingfan
 * @Date 2019-06-28
 * @Version 1.0
 **/
@Data
@Builder
@NoArgsConstructor
public class NotifyEventFactory implements EventFactory<NotifyEvent> {

    @Override
    public NotifyEvent newInstance() {
        return new NotifyEvent();
    }
}
