package com.wqf.learn.controller.test;

import com.wqf.learn.disruptor.service.MultiNotifyService;
import com.wqf.learn.disruptor.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Descrption
 * @Author wangqingfan
 * @Date 2019-06-28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/disruptor")
@Slf4j
public class DisruptorController {

    @Resource
    private NotifyService notifyService;

    @Resource
    private MultiNotifyService multiNotifyService;


    @GetMapping("{msg}")
    public String msg(@PathVariable("msg") String msg) {
        log.info("disruptor---msg：{}", msg);
        notifyService.sendNotify(msg);
        return "success";
    }

    @GetMapping("/multi/{msg}")
    public String multiMsg(@PathVariable("msg") String msg) {
        log.info("disruptor---msg：{}", msg);
        multiNotifyService.sendMsg(msg);
        return "success";
    }
}
