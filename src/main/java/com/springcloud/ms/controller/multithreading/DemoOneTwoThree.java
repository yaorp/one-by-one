package com.springcloud.ms.controller.multithreading;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用condition 执行顺序: 1 -> 2
 * @author: yaorp
 */
public class DemoOneTwoThree {

    @Test
    public void test1() {
        CountDownLatch cd1 = new CountDownLatch(1);
        CountDownLatch cd2 = new CountDownLatch(1);
        CountDownLatch cd3 = new CountDownLatch(1);

        Thread t1 = new Thread(new TestThread(cd1),"t1");
        t1.start();
        try {
            // 会等待计数器的值为0，才会往下继续执行
            cd1.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread t2 = new Thread(new TestThread(cd2),"t2");
        t2.start();
        try {
            cd2.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        Thread t3 = new Thread(new TestThread(cd3),"t3");
        t3.start();
        try {
            cd3.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

//    @Test
    public static void main(String[] args){
        HairCut hairCut = new HairCut();

        Thread t1 = new Thread(()->{
            hairCut.wash();
        });

        Thread t2 = new Thread(()->{
            hairCut.cut();
        });

        t1.start();
        t2.start();

    }
}

class TestThread implements Runnable {
    private CountDownLatch cd;

    public TestThread(CountDownLatch cd){
        this.cd = cd;
    }

    @Override
    public void run() {
//        try {
//            Thread.sleep(3*1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println(Thread.currentThread().getName()+"执行了");
        cd.countDown();
    }
}

class HairCut {
    public volatile int num = 1;
    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();

    public void wash() {
        try {
            lock.lock();

            while (num != 1) {
                c1.await();
            }
            System.out.println("开始洗头");
            Thread.sleep(3 * 1000);
            num = 2;
            c2.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void cut() {
        try {
            lock.lock();

            while (num != 2) {
                c2.await();
            }

            System.out.println("开始剪发");
            num = 1;
            c1.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}


