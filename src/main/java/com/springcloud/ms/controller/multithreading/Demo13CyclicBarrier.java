package com.springcloud.ms.controller.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: yaorp
 */
public class Demo13CyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cy = new CyclicBarrier(3,()->{
            System.out.println("召唤神龙");
        });

        for (int i=0; i<6; i++) {
            new Thread(new CyclicBarrierDemo(cy)).start();
        }
    }

    static class CyclicBarrierDemo implements Runnable{

        private CyclicBarrier cy;

        public CyclicBarrierDemo(CyclicBarrier cy) {
            this.cy = cy;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"收集到了");
                cy.await();
                System.out.println(Thread.currentThread().getName()+"飞走了");
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
