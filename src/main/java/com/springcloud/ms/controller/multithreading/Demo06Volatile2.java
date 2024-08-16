package com.springcloud.ms.controller.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yaorp
 */
public class Demo06Volatile2 {

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo demo = new VolatileDemo();
        for (int i=0; i<3; i++) {
//            Thread t = new Thread(demo);
//            t.start();
            // Thread不能重复调用start方法
            demo.start();
        }

        Thread.sleep(100);
        System.out.println("count=" + demo.count);
    }

    static class VolatileDemo extends Thread {
//        public volatile AtomicInteger count= new AtomicInteger(0);
        public volatile int count;
        private final Lock lock = new ReentrantLock();


        @Override
        public void run() {
            addCount();
        }

        private void addCount() {
//            for (int i = 0; i < 1000; i++) {
                try {
//                        System.out.println("count=" + count);
//                        Thread.sleep(100);
                    lock.lock();
                    count++;
                    lock.unlock();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
//            }
        }
    }

}
