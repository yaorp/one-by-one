package com.springcloud.ms.controller.multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一次一个人，完成之后，下一个人开始
 * @author: yaorp
 */
public class Demo14ConditionDemo {
    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();

        for (int i=0;i<2;i++){
            new Thread(()->{
                conditionDemo.wash();
            },"雄雄").start();
        }

        for (int i=0;i<2;i++){
            new Thread(()->{
                conditionDemo.cut();
            },"超超").start();
        }
    }

    static class ConditionDemo {
        private volatile int flowNum = 1;

        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        public void wash() {
            try {
                lock.lock();
                while (flowNum != 1) {
                    c1.await();
                }
                System.out.println(Thread.currentThread().getName() + "洗完了");
                flowNum = 2;
                c2.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        public void cut() {
            lock.lock();
            try {
                while (flowNum != 2) {
                    c2.await();
                }
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "剪完了");
                flowNum = 1;
                c1.signal();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
