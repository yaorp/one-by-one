package com.springcloud.ms.controller.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: yaorp
 */
public class Demo11CountDownLatch {


    public static void main(String[] args) {
        CountDownLatch cd = new CountDownLatch(4);

        for (int i=0; i<3; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+"下班了");
                cd.countDown();
            },i+"").start();
        }

        new Thread(()->{
            try {
                cd.await(7, TimeUnit.SECONDS);
                System.out.println("卷王卷不动了，下班了");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
