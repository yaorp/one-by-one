package com.springcloud.ms.controller.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal当使用线程池的时候的处理方式
 * 使用tl.remove();防止内存泄露
 * 可能还没发生GC所以要清空
 * @author: yaorp
 */
public class DemoThreadLocal2 {
    private static ThreadLocal<Integer> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(()->{
//            tl.set(123);
//            System.out.println(Thread.currentThread().getName() + "==>" + tl.get());
//        });
//
//        executorService.execute(()->{
//            System.out.println(Thread.currentThread().getName() + "==>" + tl.get());
//        });


        // 第二种方式
        executorService.execute(new ThreadLocal2(tl));
        executorService.execute(new ThreadLocal3(tl));
    }
}

class ThreadLocal2 implements Runnable{
    private ThreadLocal<Integer> tl = new ThreadLocal<>();

    public ThreadLocal2(ThreadLocal<Integer> tl) {
        tl.remove();
        this.tl = tl;
    }

    @Override
    public void run() {
        tl.set(123);
        System.out.println(Thread.currentThread().getName() + "==>" + tl.get());
        tl.remove();
    }
}

class ThreadLocal3 implements Runnable{
    private ThreadLocal<Integer> tl = new ThreadLocal<>();

    public ThreadLocal3(ThreadLocal<Integer> tl) {
        tl.remove();
        this.tl = tl;
    }

    @Override
    public void run() {
//        tl.set(123);
        System.out.println(Thread.currentThread().getName() + "==>" + tl.get());
        tl.remove();
    }
}

