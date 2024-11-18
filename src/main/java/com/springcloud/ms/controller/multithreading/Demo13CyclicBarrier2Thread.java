package com.springcloud.ms.controller.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 1，2线程一块执行
 * @author: yaorp
 */
public class Demo13CyclicBarrier2Thread {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(2,()->{
            System.out.println("大家一块执行");
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        Thread t1 = new Thread(new CyclicBarrierDemo(cb),"线程1");
        Thread t2 = new Thread(new CyclicBarrierDemo(cb),"线程2");

        t1.start();
        t2.start();

    }
}

class CyclicBarrierDemo implements Runnable{
    private CyclicBarrier cb;

    public CyclicBarrierDemo(CyclicBarrier cb){
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
