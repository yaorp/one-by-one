package com.springcloud.ms.controller.multithreading;

/**
 * 用join实现 1->2
 * @author: yaorp
 */
public class Demo24Join {

    public static void main(String[] args) {
//        Thread t1 = new Thread(()->{
//            try {
//                Thread.sleep(2*1000);
//                System.out.println("线程1执行完了");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        },"线程1");
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run(){
//                try {
//                    t1.join();
//                    Thread.sleep(1*1000);
//                    System.out.println("线程2执行完了");
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//        },"线程2");

        Thread t1 = new Thread(new JoinDemo1(null));
        Thread t2 = new Thread(new JoinDemo2(t1));

        t1.start();
        t2.start();
        System.out.println("主线程执行完了");
    }
}

class JoinDemo1 implements Runnable{
    private Thread t;

    public JoinDemo1(Thread t){
        this.t = t;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(2*1000);
            System.out.println("线程1执行完了");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class JoinDemo2 implements Runnable{
    private Thread t;

    public JoinDemo2(Thread t){
        this.t = t;
    }

    @Override
    public void run(){
        try {
            t.join();
            Thread.sleep(1*1000);
            System.out.println("线程2执行完了");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
