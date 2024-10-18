package com.springcloud.ms.controller.multithreading;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author: yaorp
 */
public class Demo22Future2 {
    public static void main(String[] args) {
        ThreadFactory tfBuild = new ThreadFactoryBuilder().setNameFormat("pool-test-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 20, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1),tfBuild,new ThreadPoolExecutor.AbortPolicy());

        Future<String> future = threadPoolExecutor.submit(new FutureDemo());
        Future<String> future1 = threadPoolExecutor.submit(new FutureDemo());
        Future<String> future2 = threadPoolExecutor.submit(new FutureDemo());
        Future<String> future3 = threadPoolExecutor.submit(new FutureDemo());
        Future<String> future4 = threadPoolExecutor.submit(new FutureDemo());
        try {
            System.out.println(future.get());
            System.out.println(future1.get());
            System.out.println(future2.get());
            System.out.println(future3.get());
            System.out.println(future4.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


}

class FutureDemo implements Callable {

    @Override
    public String call(){
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Thread.currentThread().getName();
    }
}
