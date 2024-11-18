package com.springcloud.ms.controller.multithreading;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 用线程池-手动创建线程池
 * 1，2线程一块执行
 * @author: yaorp
 */
public class Demo13CyclicBarrier4ThreadPoolExecutor {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(2,()->{
            System.out.println("大家一块执行");
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        ThreadFactory tf = new ThreadFactoryBuilder().setNameFormat("test-pool-%d").build();
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 5, 6L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024),tf);
        tpe.execute(new CyclicBarrierDemo4(cb));
        tpe.execute(new CyclicBarrierDemo4(cb));
        tpe.execute(new CyclicBarrierDemo4(cb));
        tpe.execute(new CyclicBarrierDemo4(cb));

    }
}

class CyclicBarrierDemo4 implements Runnable{
    private CyclicBarrier cb;

    public CyclicBarrierDemo4(CyclicBarrier cb){
        this.cb = cb;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+"------准备好了");
        try {
            cb.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        System.out.println("一块执行------"+Thread.currentThread().getName());
    }
}
