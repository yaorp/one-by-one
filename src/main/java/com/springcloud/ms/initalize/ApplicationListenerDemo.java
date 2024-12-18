package com.springcloud.ms.initalize;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: yaorp
 */
@Component
public class ApplicationListenerDemo implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("ApplicationStartedEvent ----开始----");
//        try {
//            Thread.sleep(3*1000L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("ApplicationStartedEvent ----结束----");
    }
}
