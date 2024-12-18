package com.springcloud.ms.initalize;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: yaorp
 */
@Component
public class PostConstructDemo {

    @PostConstruct
    public void initData(){
        System.out.println("PostConstruct ----开始----");
//        try {
//            Thread.sleep(3*1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("PostConstruct ----结束----");
    }
}
