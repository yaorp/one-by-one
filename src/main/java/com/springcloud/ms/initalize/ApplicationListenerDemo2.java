package com.springcloud.ms.initalize;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: yaorp
 */
@Component
public class ApplicationListenerDemo2 implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("ApplicationReadyEvent ----开始----");
//        try {
//            Thread.sleep(3*1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("ApplicationReadyEvent ----结束----");
    }
}
