package com.springcloud.ms.controller.multithreading;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: yaorp
 */
public class Demo16ArrayBlockingQueue {
    static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

    public static void main(String[] args) {
        new Thread(new Interviewer(queue)).start();
        new Thread(new Engineers(queue)).start();
    }

    static class Interviewer implements Runnable {
        private ArrayBlockingQueue<String> queue;

        public Interviewer(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run(){
            System.out.println("面试官准备好了");
            String msg = null;

            try {
                while (!(msg = queue.take()).equals("stop")) {
                    System.out.println("程序员：" + msg + "开始面试");
                    Thread.sleep(1000);
                    System.out.println("程序员：" + msg + "面试结束");
                }
                System.out.println("所有候选人都结束了");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    static class Engineers implements Runnable{
        private ArrayBlockingQueue<String> queue;

        public Engineers(ArrayBlockingQueue<String> queue){
            this.queue = queue;
        }

        @Override
        public void run(){
            for (int i=0; i<6; i++){
                String engineersNm = "程序员"+i;

                try {
                    queue.put(engineersNm);
                    System.out.println(engineersNm+"进入面试官面试队列");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                queue.put("stop");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
