package com.springcloud.ms.controller.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用线程池-固定线程池
 * 1，2线程一块执行
 * @author: yaorp
 */
public class Demo13CyclicBarrier3 {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(2,()->{
            System.out.println("大家一块执行");
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new CyclicBarrierDemo3(cb));
        executorService.execute(new CyclicBarrierDemo3(cb));
        executorService.execute(new CyclicBarrierDemo3(cb));
        executorService.execute(new CyclicBarrierDemo3(cb));

    }
}

class CyclicBarrierDemo3 implements Runnable{
    private CyclicBarrier cb;

    public CyclicBarrierDemo3(CyclicBarrier cb){
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
