package com.springcloud.ms.controller.multithreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: yaorp
 */
public class Demo17FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i=0; i<10; i++){
            executorService.execute(new TaskDemo());
        }
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
