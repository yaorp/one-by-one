package com.springcloud.ms.controller.multithreading;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author: yaorp
 */
public class DemoCurrentHashMap1 {
    public static void demo1() {
        final Map<String, Integer> count = new ConcurrentHashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(2);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                Integer oldValue ,newValue;
                for (int i = 0; i < 5; i++) {
                    while (true) {
                        oldValue = count.get("a");
                        newValue = oldValue==null ? 1 : oldValue + 1;
                        if (null == oldValue) {
                            if (count.putIfAbsent("a", newValue) == null) {
                                break;
                            }
                        } else {
                            if (count.replace("a",oldValue, newValue)){
                                break;
                            }
                        }
                    }

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
