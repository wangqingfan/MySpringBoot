package com.wqf.learn.disruptor;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Descrption 生产数据类
 /*
 * @Author wangqingfan
 * @Date 2019-06-28
 * @Version 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotifyEvent {

    @JsonPropertyDescription("消息体")
    private String messgae;
}
