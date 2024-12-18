package com.springcloud.ms.controller.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * @author: yaorp
 */
public class EventDemo extends ApplicationEvent {

    public EventDemo(RegisterInfo registerInfo) {
        super(registerInfo);
    }
}
