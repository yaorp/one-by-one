package com.springcloud.ms.controller.multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: yaorp
 */
public class Demo08ABA {
    static AtomicInteger ar = new AtomicInteger(100);
    static AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=============ABA问题的产生=============");

        Thread t1 = new Thread(() ->{
            ar.compareAndSet(100,101);
            ar.compareAndSet(101,100);
        },"t1");
        t1.start();

        Thread t2 = new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(ar.compareAndSet(100, 2024)+"\t"+ar.get());
        },"t2");
        t2.start();

        t1.join();
        t2.join();

        System.out.println("=============ABA问题的解决=============");
        new Thread(()->{
            int stamp = asr.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第1次版本号："+stamp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            asr.compareAndSet(100,101,asr.getStamp(),asr.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第2次版本号："+asr.getStamp());

            asr.compareAndSet(101,100,asr.getStamp(),asr.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第3次版本号："+asr.getStamp());
        },"t3").start();

        new Thread(()->{
            int stamp = asr.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第1次版本号："+stamp);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean result = asr.compareAndSet(100,2024,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t修改成功与否："+result+"\t当前最新实际版本号："+asr.getStamp());
        },"t4").start();

    }
}
