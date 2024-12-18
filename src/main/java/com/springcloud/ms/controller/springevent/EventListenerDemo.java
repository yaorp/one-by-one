package com.springcloud.ms.controller.springevent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author: yaorp
 */
@Component
public class EventListenerDemo {

    @EventListener(EventDemo.class)
    public void handleEvent(EventDemo eventDemo) {
        RegisterInfo registerInfo =(RegisterInfo) eventDemo.getSource();
        System.out.println("商户："+registerInfo.getUserId()+"，消息发送成功");
    }
}
