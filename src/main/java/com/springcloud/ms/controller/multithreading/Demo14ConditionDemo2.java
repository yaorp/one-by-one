package com.springcloud.ms.controller.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 雄雄一直洗，超超一直剪，雄雄不需要等超超
 * @author: yaorp
 */
public class Demo14ConditionDemo2 {
    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();

        for (int i=0;i<2;i++){
            new Thread(()->{
                conditionDemo.wash();
            },"雄雄"+i).start();
        }

        for (int j=0;j<2;j++){
            System.out.println("j="+j);
            new Thread(()->{
                conditionDemo.cut();
            },"超超"+j).start();
        }
    }

    static class ConditionDemo {
        private volatile int flowNum = 1;
        private volatile AtomicInteger needCutNum = new AtomicInteger(0);

        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        public void wash() {

            try {

                lock.lock();
                System.out.println(Thread.currentThread().getName() + "洗完了");
                Thread.sleep(2*1000);
//                flowNum = 2;
                needCutNum.getAndAdd(1);
//                c2.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        public void cut() {
            lock.lock();
            try {
                while (needCutNum.get()<=0) {
                    c2.await();
                }
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "剪完了,"+needCutNum.get());
//                flowNum = 1;
                c1.signal();
                needCutNum.getAndAdd(-1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
