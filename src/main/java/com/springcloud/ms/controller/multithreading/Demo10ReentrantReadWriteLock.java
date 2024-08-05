package com.springcloud.ms.controller.multithreading;

import lombok.Locked;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: yaorp
 */
public class Demo10ReentrantReadWriteLock {
    private static volatile  int count = 0;


    public static void main(String[] args) {
        ReentrantReadWriteLock re = new ReentrantReadWriteLock();
        WriteDemo writeDemo = new WriteDemo(re);
        ReadDemo readDemo = new ReadDemo(re);

        for (int i=0; i<3; i++) {
            new Thread(writeDemo).start();
        }

        for (int i=0; i<5; i++) {
            new Thread(readDemo).start();
        }

    }


    static class WriteDemo implements Runnable {
        ReentrantReadWriteLock lock;

        public WriteDemo(ReentrantReadWriteLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            lock.writeLock().lock();
            count++;
            System.out.println("写锁：" + count);
            lock.writeLock().unlock();

        }
    }

    static class ReadDemo implements Runnable {
        private ReentrantReadWriteLock lock;
        public ReadDemo(ReentrantReadWriteLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            lock.readLock().lock();
            System.out.println("读锁：" + count);
            lock.readLock().unlock();
        }
    }
}


