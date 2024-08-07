package com.springcloud.ms.controller.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: yaorp
 */
public class Demo21ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
//        scheduledExecutorService.schedule(new TaskDemo(),5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new TaskDemo(),1,3,TimeUnit.SECONDS);
    }
    static class TaskDemo implements Runnable{
        @Override
        public void run(){
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
