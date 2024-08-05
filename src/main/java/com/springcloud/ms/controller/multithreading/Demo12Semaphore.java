package com.springcloud.ms.controller.multithreading;

import javax.sound.midi.Soundbank;
import java.util.concurrent.Semaphore;

/**
 * @author: yaorp
 */
public class Demo12Semaphore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3,true);
        for (int i=0; i<6; i++) {
            new Thread(new SemaphoreDemo(semaphore)).start();
        }
    }

    static class SemaphoreDemo implements Runnable {

        private Semaphore semaphore;

        public SemaphoreDemo(Semaphore semaphore) {
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+"抢到了车位");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"停车1秒后离开了车位");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                semaphore.release();
            }
        }
    }

}
