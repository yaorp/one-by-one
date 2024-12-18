package com.springcloud.ms.controller.springevent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: yaorp
 */
@RestController
@RequestMapping("/myTest")
public class EventPublisherDemo {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * springEvent测试demo
     * @return
     */
    @GetMapping("/userRegister")
    public String userRegister(){
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setUserId("123456");
        EventDemo eventDemo = new EventDemo(registerInfo);
        applicationContext.publishEvent(eventDemo);
        return "消息发送成功";
    }
}
