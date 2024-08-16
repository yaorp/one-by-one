package com.springcloud.ms.controller.multithreading;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yaorp
 */
public class DemoCurrentHashMap2 {
    public static void demo1() {
        final Map<String, AtomicInteger> count = new ConcurrentHashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(2);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                AtomicInteger oldValue ;
                for (int i = 0; i < 5; i++) {
                    oldValue = count.get("a");
                    if (null == oldValue) {
                        AtomicInteger newValue = new AtomicInteger(0);
                        oldValue = count.putIfAbsent("a", newValue);
                        if (oldValue ==null) {
                            oldValue = newValue;
                        }
                    }

                    oldValue.incrementAndGet();

                }
                endLatch.countDown();
            }
        };
        new Thread(task).start();
        new Thread(task).start();

        try {
            endLatch.await();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        demo1();
    }
}
