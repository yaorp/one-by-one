package com.springcloud.ms.controller.multithreading;

/**
 * @author: yaorp
 */
public class WaiteTest {
    private static Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            synchronized (monitor) {

                try {
                    System.out.println("t1 线程调用waite方法，进入休眠");
                    monitor.wait();
                    System.out.println("t1 线程被唤醒了，继续执行");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"t1-thread");

        Thread t2 = new Thread(()->{
            synchronized (monitor) {

                try {
                    System.out.println("t2 线程调用waite方法，进入休眠");
                    monitor.wait();
                    System.out.println("t2 线程被唤醒了，继续执行");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"t2-thread");

        t1.start();
        t2.start();
        Thread.sleep(3*1000L);
        System.out.println("查看一下 t1 线程当前的状态："+t1.getState());
        synchronized (monitor) {
            System.out.println("main线程获得了锁，然后调用notify方法");
            monitor.notify();
        }
    }
}
