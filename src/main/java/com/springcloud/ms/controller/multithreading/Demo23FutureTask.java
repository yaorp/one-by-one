package com.springcloud.ms.controller.multithreading;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author: yaorp
 */
public class Demo23FutureTask {
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

        FutureTask<Integer> integerFutureTask = new FutureTask<>(new CallableDemo());
        pool.submit(integerFutureTask);

        try {
            System.out.println(integerFutureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        pool.shutdown();
    }

    static class CallableDemo implements Callable<Integer> {

        @Override
        public Integer call(){
//            return new Random().nextInt();
            try {
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        }
    }
}
