package com.springcloud.ms.controller.multithreading;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author: yaorp
 */
public class Demo22Future {
    public static void main(String[] args) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("test-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(
                5,
                200,
                0L,
                TimeUnit.SECONDS
                ,new LinkedBlockingQueue<>(1024),
                namedThreadFactory
                );

//        ExecutorService pool = Executors.newFixedThreadPool(3);
        Future<Integer> submit = pool.submit(new CallableDemo());

        try {
            System.out.println(submit.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        pool.shutdown();
    }

    static class CallableDemo implements Callable<Integer> {

        @Override
        public Integer call(){
//            return new Random().nextInt();
//            try {
//                Thread.sleep(10*1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            System.out.println(Thread.currentThread().getName());
            return 1;
        }
    }
}
